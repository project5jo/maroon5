package com.spring.mood.projectmvc.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class FileUploadUtil {

    public static String uploadFile (String rootPath, MultipartFile file) {

        // 프로필사진 업로드
        // 1. 원본파일명을 랜덤파일명으로 변경하기
        String randomName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // 2. 첨부파일을 날짜별로 저장할 날짜폴더 경로 생성
        String datePath = makeDateFormatDirectory(rootPath);

        // 3. 날짜폴더에 랜덤명첨부파일 넣기 & 예외처리
        try {
            file.transferTo(new File(datePath, randomName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 4. 업로드 후 DB 에 파일경로 저장
        String fullPath = datePath + "/" + randomName;

        String urlPath = "/file" + fullPath.substring(rootPath.length());

        return urlPath;
    }

    // 2. 첨부파일을 날짜별로 저장할 날짜폴더 경로 생성 함수
    private static String makeDateFormatDirectory(String rootPath) {

        // 오늘 날짜의 연, 월, 일 추출하기
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        // 폴더 생성
        String dateDirectory = rootPath;

        List<String> dateList = List.of("" + year, twoString(month), twoString(day));

        String dateRootPath = rootPath;

        for (String date : dateList) {
            dateRootPath += "/" + date;

            File datefile = new File(dateRootPath);

            if (!datefile.exists()) {
                datefile.mkdirs();
            }
        }
        return dateRootPath;
    }

    // Month, day 두자리가 아닐 경우 두자리로 포맷팅해주는 함수
    private static String twoString (int n) {
        DecimalFormat df = new DecimalFormat("00");
        String formatNumber = df.format(n);

        return formatNumber;
    }
}
