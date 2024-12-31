package DAO;

import java.io.IOException;
import java.util.List;

public interface DataImportExportI <T>{
	int importData(String fileName) throws IOException;
	void exportData(String fileName) throws IOException;
}
