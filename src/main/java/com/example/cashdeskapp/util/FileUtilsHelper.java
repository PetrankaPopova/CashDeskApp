package com.example.cashdeskapp.util;

import com.example.cashdeskapp.dto.CashBalanceDTO;
import com.example.cashdeskapp.dto.CashOperationDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.time.LocalDateTime;


public class FileUtilsHelper  {

    private static final String BALANCES_CASH_FILE = "src/main/resources/cash_balances.txt";
    private static final String OPERATIONS_CASH_FILE = "src/main/resources/cash_operations.txt";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();

    public static void recordCashOperation(CashOperationDTO cashOperationDTO) throws IOException {
        cashOperationDTO.setLocalDateTime(LocalDateTime.now());
        try (FileWriter writer = new FileWriter(OPERATIONS_CASH_FILE, true)) {
            gson.toJson(cashOperationDTO, writer);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + OPERATIONS_CASH_FILE, e);
        }
    }

    public static void recordCashBalance(CashBalanceDTO cashBalanceDTO) throws IOException {
        cashBalanceDTO.setLocalDateTime(LocalDateTime.now());
        try (FileWriter writer = new FileWriter(BALANCES_CASH_FILE, true)) {
            gson.toJson(cashBalanceDTO, writer);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + BALANCES_CASH_FILE, e);
        }
    }

    public static CashBalanceDTO readLastBalance() throws IOException {
        File file = new File(BALANCES_CASH_FILE);

         if (!file.exists() || file.length() == 0) {
            throw new IOException("File is empty or does not exist: " + BALANCES_CASH_FILE);
        }

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            long fileLength = randomAccessFile.length();
            long pointer = fileLength - 1;


            boolean insideJson = false;
            int openBraceCount = 0;
            ByteArrayOutputStream jsonBytes = new ByteArrayOutputStream();

            while (pointer >= 0) {
                randomAccessFile.seek(pointer);
                char c = (char) randomAccessFile.readByte();


                if (c == '}') {
                    insideJson = true;
                    openBraceCount++;
                }

                if (insideJson) {
                    jsonBytes.write(c);
                }

                if (c == '{') {
                    openBraceCount--;
                    if (openBraceCount == 0) {
                        break;
                    }
                }

                pointer--;
            }

            String json = new StringBuilder(jsonBytes.toString("UTF-8")).reverse().toString().trim();

            return gson.fromJson(json, CashBalanceDTO.class);
        } catch (JsonSyntaxException e) {
            throw new IOException("Error reading from file or parsing JSON: " + BALANCES_CASH_FILE, e);
        }
    }

    public static boolean isCashBalanceFileEmpty() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(BALANCES_CASH_FILE, "r")) {
            return file.length() == 0;
        } catch (IOException e) {
            throw new IOException("Error checking the file: " + BALANCES_CASH_FILE, e);
        }
    }


}