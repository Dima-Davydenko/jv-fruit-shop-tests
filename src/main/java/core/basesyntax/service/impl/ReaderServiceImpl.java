package core.basesyntax.service.impl;

import core.basesyntax.service.ReaderService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ReaderServiceImpl implements ReaderService {
    @Override
    public List<String> read(String filePath) {
        if (filePath == null) {
            throw new RuntimeException("Path to file can't be null.");
        }
        File file = new File(filePath);
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + filePath, e);
        }
    }
}