package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.copyOf;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void saveToArray(Resume resume, int index);

    @Override
    public final int size() {
        return size;
    }

    @Override
    protected void saveResume(Resume resume, Integer key) {
        if (size < STORAGE_LIMIT) {
            saveToArray(resume, key);
            size++;
        } else {
            throw new StorageException("The storage is overflow.", resume.getUuid());
        }
    }

    @Override
    protected boolean isExist(Integer key) {
        return key >= 0;
    }

    @Override
    protected Resume getResume(Integer key) {
        return storage[key];
    }

    @Override
    protected void updateResume(Resume resume, Integer key) {
        storage[key] = resume;
    }

    @Override
    protected final void deleteResume(Integer key) {
        int index = key;
        System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
        size--;
    }

    @Override
    protected void clearStorage() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public final List<Resume> toList() {
        return Arrays.asList(copyOf(storage, size));
    }
}