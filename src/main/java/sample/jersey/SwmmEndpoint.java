package sample.jersey;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

/**
 * Created by aaron on 16-5-15.
 */

@Component
@Path("/rest")
public class SwmmEndpoint {

    private SwmmService swmmService;

    @Autowired
    public SwmmEndpoint(SwmmService service) {
        this.swmmService = service;
    }

    @GET
    @Path("/report")
    public String getReport(){
        File report = new File(SwmmService.DATA_DIR + SwmmService.REPORT_FILENAME);
       if(!report.exists()){
           return "";
       }

        StringBuilder reportString = new StringBuilder();
        reportString.append("<PRE>");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(report));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                reportString.append(tempString).append("\n");
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        reportString.append("</PRE>");

        return reportString.toString();
    }

    @POST
    @Path("/upload")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public Response uploadPdfFile(@FormDataParam("file") InputStream fileInputStream,
                                  @FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {

        try {
            int read = 0;
            byte[] bytes = new byte[1024];

            OutputStream out = new FileOutputStream(new File(SwmmService.DATA_DIR + fileMetaData.getFileName()));
            while ((read = fileInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new WebApplicationException("Error while uploading file. Please try again !!");
        }
        swmmService.setInputFileName(fileMetaData.getFileName());
        swmmService.run();

        return Response.ok("Data uploaded successfully !!").build();
    }

}
