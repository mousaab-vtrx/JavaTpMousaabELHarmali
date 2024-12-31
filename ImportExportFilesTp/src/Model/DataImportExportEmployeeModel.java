package Model;

import DAO.DAOImportExportEmployeeImplement;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataImportExportEmployeeModel {
    private final DAOImportExportEmployeeImplement dao;
    private static final Logger LOGGER = Logger.getLogger(DataImportExportEmployeeModel.class.getName());

    public DataImportExportEmployeeModel(DAOImportExportEmployeeImplement dao) {
        this.dao = dao;
    }

    public int importData(File file) throws IOException {
        validateFile(file, false);
        try {
            return dao.importData(file.getAbsolutePath());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error importing data from file: " + file.getAbsolutePath(), e);
            throw new IOException("Error importing data", e);
        }
    }

    public void exportData(File file) throws IOException {
        validateFile(file, true);
        try {
            dao.exportData(file.getAbsolutePath());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error exporting data to file: " + file.getAbsolutePath(), e);
            throw new IOException("Error exporting data", e);
        }
    }

    private void validateFile(File file, boolean isExport) throws IOException {
        if (file == null) {
            throw new IOException("File is null");
        }
        if (isExport && file.exists() && !file.canWrite()) {
            throw new IOException("Cannot write to the specified file: " + file.getAbsolutePath());
        }
        if (!isExport && (!file.exists() || !file.isFile() || !file.canRead())) {
            throw new IOException("Cannot read the specified file: " + file.getAbsolutePath());
        }
    }
}
