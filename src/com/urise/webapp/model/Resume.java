package com.urise.webapp.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {
    // Unique identifier
    private final String uuid;
    private final String fullName;
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private Map<SectionType, AbstractSection<?>> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setSection(SectionType key, AbstractSection<?> value) {
        sections.put(key, value);
    }

    public void setContact(ContactType key, String value) {
        contacts.put(key, value);
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public AbstractSection<?> getSection(SectionType type) {
        return sections.get(type);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(uuid + "\n" + fullName + "\n\n" + SectionType.CONTACTS.getTitle() + "\n");
        for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
            ContactType ct = entry.getKey();
            String contact = entry.getValue();
            sb.append(ct.getTitle()).append(contact).append("\n");
        }
        sb.append("\n");
        for (Map.Entry<SectionType, AbstractSection<?>> entry : sections.entrySet()) {
            SectionType st = entry.getKey();
            AbstractSection<?> section = entry.getValue();
            sb.append(st.getTitle()).append("\n").append(section).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!getUuid().equals(resume.getUuid())) return false;
        if (!getFullName().equals(resume.getFullName())) return false;
        if (contacts != null ? !contacts.equals(resume.contacts) : resume.contacts != null) return false;
        return sections != null ? sections.equals(resume.sections) : resume.sections == null;
    }

    @Override
    public int hashCode() {
        int result = getUuid().hashCode();
        result = 31 * result + getFullName().hashCode();
        result = 31 * result + (contacts != null ? contacts.hashCode() : 0);
        result = 31 * result + (sections != null ? sections.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Resume resume) {
        return fullName.equalsIgnoreCase(resume.getFullName()) ?
                uuid.compareTo(resume.getUuid()) :
                fullName.compareTo(resume.getFullName());
    }
}