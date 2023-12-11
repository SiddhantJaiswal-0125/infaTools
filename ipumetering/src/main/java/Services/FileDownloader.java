package Services;

import org.example.Modals.FileStructure;
import org.example.Modals.InitiatorTaskResponse;
import org.example.Modals.LoginResponse;
import org.example.Modals.StatusCheckerResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileDownloader {
    public static List<FileStructure> downloadFile(LoginResponse currentSession, InitiatorTaskResponse initiatorTaskResponse,
                                                   StatusCheckerResponse statusCheckerResponse,String parentDir) throws IOException {
        String url = currentSession.getServerUrl()+Utilities.downloadMeteringDataURL+statusCheckerResponse.getJobId()+"/download";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("INFA-SESSION-ID", currentSession.getIcSessionId());
        String requestBody = null;

        HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);

        List<FileStructure> unzippedFiles = new ArrayList<>();

        // Request the file
        ResponseEntity<Resource> response = restTemplate.exchange(url, HttpMethod.GET, entity, Resource.class);
        byte[] buffer = new byte[40000];
        // Check if the response has a body, and it's readable

        if (response.hasBody() && response.getBody().isReadable()) {
            // Read the file from the response
            try (InputStream in = response.getBody().getInputStream()) {
                // Write the file (zip) to the local filesystem
                try (OutputStream out = new FileOutputStream("downloaded_file.zip")) {

                    int bytesRead;

                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
            }

            // Unzip the file
            try (ZipInputStream zis = new ZipInputStream(new FileInputStream("downloaded_file.zip"))) {
                ZipEntry zipEntry = zis.getNextEntry();

                while (zipEntry != null) {
                    File newFile = new File(parentDir  +"/unzipped_files/" + zipEntry.getName());


                    FileStructure curr = new FileStructure();

                    curr.setFileName(zipEntry.getName());
                    curr.setFilePath(newFile.getPath());
                    unzippedFiles.add(curr);
                    // Create all non-exists folders
                    // else you will hit FileNotFoundException for compressed folder
                    new File(newFile.getParent()).mkdirs();

                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }

                    zipEntry = zis.getNextEntry();
                }


                zis.closeEntry();
            }
        }
        deleteFile("downloaded_file.zip");





        return unzippedFiles;
    }

    static  boolean deleteFile(String path)
    {
        System.out.println("Deleting file at path : "+path);
        try
        {
            File f= new File(path);           //file to be delete
            if(f.delete())                      //returns Boolean value
            {
                System.out.println(f.getName() + " deleted");   //getting and printing the file name

                return  true;
            }
            else
            {
                System.out.println("failed");
            return  false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        return  false;
        }

    }
}
