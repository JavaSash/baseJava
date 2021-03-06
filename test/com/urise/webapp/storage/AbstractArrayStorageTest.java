package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveStorageOverflow() {
        String noName = "no name";
        try {
            for (int i = 4; i <= STORAGE_LIMIT; i++) {
                storage.save(new Resume(noName));
            }
        } catch (StorageException exc) {
            Assert.fail("Storage overflowed early");
        }
        storage.save(new Resume(noName));
    }
}