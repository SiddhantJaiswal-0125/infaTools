package Services;

import org.example.Modals.InitiatorTaskResponse;
import org.example.Modals.LoginResponse;
import org.example.Modals.StatusCheckerResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileDownloader {
    public static void downloadFile(LoginResponse currentSession, InitiatorTaskResponse initiatorTaskResponse, StatusCheckerResponse statusCheckerResponse) throws IOException {
        String url = currentSession.getServerUrl()+Utilities.downloadMeteringDataURL+statusCheckerResponse.getJobId()+"/download";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("INFA-SESSION-ID", currentSession.getIcSessionId());
        String requestBody = null;

        HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);


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
                    File newFile = new File("unzipped_files/" + zipEntry.getName());
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
    }
}
