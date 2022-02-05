package it.siliconsquare.connection.ftp;

import it.siliconsquare.common.redirect.ResourcePath;
import it.siliconsquare.logger.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FtpManager implements Ftp {

    private FtpConfig ftpConfig;

    @Override
    public boolean uploadFile(String path, InputStream inputStream) {
        FTPClient ftpClient = createFtpClient();
        boolean result = false;
        try {
            boolean done = ftpClient.storeFile(path, inputStream);
            if (done) {
                Logger.getInstance().captureMessage("The file in path[" + path + "] is uploaded successfully.");
                result = true;
                close(ftpClient);
            } else
                Logger.getInstance().captureMessage("FAIL, file in path[" + path + "] NOT uploaded.");

        } catch (IOException e) {
            Logger.getInstance().captureException(e, "FAIL, file in path[" + path + "] NOT uploaded.");
        } finally {
            return result;
        }
    }

    @Override
    public String uploadFile(ResourcePath resourcePath, MultipartFile multipartFile) {
        boolean done = false;
        String fileName = multipartFile.getOriginalFilename().replaceAll(" ", "_");
        if (fileName.contains(".png") || fileName.contains(".gif") || fileName.contains(".jpg")
                || fileName.contains(".jpeg")) {
            // get current date time and seconds
            String randomValue = System.currentTimeMillis() + "";

            String path = resourcePath.value + randomValue + "_" + fileName;
            try {
                InputStream inputStream = multipartFile.getInputStream();
                done = uploadFile(path, inputStream);
                inputStream.close();
            } catch (IOException e) {
                Logger.getInstance().captureException(e, "FAIL, file in path[" + path + "] NOT uploaded.");
            } finally {
                if (done)
                    return ResourcePath.ROOT.value + path;
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean createFolder(String path) {
        // FTPClient ftpClient = createFtpClient();
        // try {
        // ftpClient.makeDirectory(path);
        // var result = findFtpFile(path, ftpClient);
        // close(ftpClient);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // return result;
        return false;
    }

    @Override
    public List<String> listRoot() {
        return null;
    }

    @Override
    public List<String> list(String path) {
        return null;
    }

    @Override
    public boolean deleteFile(String fileName) {
        try {
            fileName = fileName.replace(ResourcePath.ROOT.value, "");
            FTPClient ftpClient = createFtpClient();
            if (ftpClient.deleteFile(fileName))
                Logger.getInstance().captureMessage("File [" + fileName + "deleted successfully");
        } catch (IOException e) {
            Logger.getInstance().captureException(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteFolder(String path) {
        return false;
    }

    @Override
    public Collection<String> listFiles(String path) {
        FTPClient ftp = createFtpClient();
        FTPFile[] files = new FTPFile[0];
        try {
            files = ftp.listFiles(path);
        } catch (IOException e) {
            Logger.getInstance().captureException(e);
        }
        return Arrays.stream(files).map(FTPFile::getName).collect(Collectors.toList());
    }

    @Override
    public void downloadFile(String source, String destination) {
        try {
            FTPClient ftp = createFtpClient();
            FileOutputStream out = new FileOutputStream(destination);
            ftp.retrieveFile(source, out);
            out.close();
        } catch (Exception e) {
            Logger.getInstance().captureException(e);
        }
    }

    private boolean findFtpFile(String path, FTPClient ftpClient) {
        return true;
    }

    private FTPClient createFtpClient() {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpConfig = new FtpConfig();
            ftpClient.setAutodetectUTF8(true);
            ftpClient.connect(ftpConfig.getServer(), ftpConfig.getPort());
            ftpClient.enterLocalPassiveMode();
            ftpClient.enterRemotePassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            if (!ftpClient.login(ftpConfig.getUsername(), ftpConfig.getPassword())
                    || !FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {

                ftpClient.disconnect();
                throw new RuntimeException("Invalid FTP credentials");
            }

        } catch (Exception e) {
            Logger.getInstance().captureException(e);
        }

        return ftpClient;
    }

    private void close(FTPClient ftpClient) {
        try {
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException e) {
            Logger.getInstance().captureException(e);
        }
    }
}
