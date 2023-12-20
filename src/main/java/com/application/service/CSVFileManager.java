package com.application.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileManager {
	private String filePath;

	public CSVFileManager(String filePath) {
		this.filePath = filePath;
	}

//	Trong trường hợp sử dụng khối try-with-resources, CSVReader sẽ tự động đóng tệp tin khi kết thúc khối try, 
//	không cần phải thực hiện việc đóng tệp tin trong khối finally. 
//	Điều này đảm bảo rằng tệp tin sẽ được đóng một cách đúng đắn ngay cả khi có ngoại lệ xảy ra hoặc khi quá 
//	trình đọc dữ liệu hoàn tất.
	public List<String[]> readData() {
		List<String[]> data = null;
		try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
			data = reader.readAll();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}
		return data;
	}

	public List<String[]> readRow() {
		List<String[]> data = new ArrayList<>();
		try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
			String[] row;
			while ((row = reader.readNext()) != null) {
				data.add(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvValidationException e) {
			e.printStackTrace();
		}
		return data;
	}

	public void writeData(List<String[]> data) {
		try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
			writer.writeAll(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeRow(String[] rowData) {
		try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true))) {
			writer.writeNext(rowData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}