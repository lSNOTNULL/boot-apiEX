package org.example.bootapiex.model.form;

import org.springframework.web.multipart.MultipartFile;

//public record DiaryForm(MultipartFile file) {
public record DiaryForm(String title, String content, MultipartFile file) {
    public static DiaryForm empty() {

        return new DiaryForm("","",null); // 바뀐 Diary entity에 맞게 변경
    }
}
