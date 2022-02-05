package it.siliconsquare.connection.ftp;

import it.siliconsquare.common.redirect.ResourcePath;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

public interface Ftp {

    /**
     * @param path        The path of the file once uploaded. <br>
     *                    The path is relative to the root of the FTP server.
     *                    The path can include the file name.
     * @param inputStream
     * @return
     */
    boolean uploadFile(String path, InputStream inputStream);

    String uploadFile(ResourcePath path, MultipartFile multipartFile);

    boolean createFolder(String path);

    List<String> listRoot();

    List<String> list(String path);

    boolean deleteFile(String path);

    boolean deleteFolder(String path);

    Collection<String> listFiles(String path);

    void downloadFile(String source, String destination);
}
