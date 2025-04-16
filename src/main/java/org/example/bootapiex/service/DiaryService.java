package org.example.bootapiex.service;

import org.example.bootapiex.model.entity.Diary;

import java.util.List;

public interface DiaryService {
    List<Diary> getAllDiaryList() throws  Exception;

    Diary createDiary(Diary diary) throws Exception;
}