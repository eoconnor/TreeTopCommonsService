package com.treetopcommons.util;

import com.treetopcommons.models.Organization;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CSVOrganizationParser {

    private static final String DATABASE_FILE_NAME = "organization_sample_data.csv";

    public static List<Organization> parseFile() {
        List<Organization> orgList = new ArrayList<>();

        BufferedReader bufferedReader = null;
        String line;
        String fieldSeparator = ",";
        boolean isHeaderRecord = true;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(
                    ClassLoader.getSystemClassLoader().getResourceAsStream(DATABASE_FILE_NAME)));
            while ((line = bufferedReader.readLine()) != null) {
                if (isHeaderRecord) {
                    isHeaderRecord = false;
                } else {
                    String[] orgRecord = line.split(fieldSeparator);
                    orgList.add(convertRecordToOrg(orgRecord));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return orgList;
    }

    private static Organization convertRecordToOrg(String[] orgRecord) {
        return new Organization(
                orgRecord[0],
                orgRecord[1],
                orgRecord[2],
                orgRecord[3],
                orgRecord[4],
                orgRecord[5]);
    }
}
