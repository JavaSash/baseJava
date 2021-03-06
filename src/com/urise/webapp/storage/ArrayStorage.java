package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected void saveToArray(Resume resume, int index) {
        index = size;
        storage[index] = resume;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equalsIgnoreCase(uuid)) {
                return i;
            }
        }
        return -1;
    }
}