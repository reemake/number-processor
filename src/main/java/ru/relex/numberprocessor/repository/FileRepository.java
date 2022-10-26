package ru.relex.numberprocessor.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Repository
@Getter
@Setter
public class FileRepository {

    private String filepath;

    public String generateChecksum(MessageDigest digest) throws IOException {

        byte[] byteArray = Files.readAllBytes(Path.of(this.filepath));
        digest.update(byteArray, 0, byteArray.length);
        byte[] bytes = digest.digest();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< bytes.length ; i++)  {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public String getHash() throws NoSuchAlgorithmException, IOException {
        MessageDigest md5Digest = MessageDigest.getInstance("MD5");
        return generateChecksum(md5Digest);
    }
}
