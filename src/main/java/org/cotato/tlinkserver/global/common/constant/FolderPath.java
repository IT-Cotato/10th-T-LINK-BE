package org.cotato.tlinkserver.global.common.constant;

public enum FolderPath {
    LECTURE("lecture"), HOMEWORK("homework"), BANK_LOGO("bank-logo");

    private static final String DELIMITER = "/";
    private final String path;

    FolderPath(String path) {
        this.path = path;
    }

    public static String generate(FolderPath folderPath, String key) {
        return String.join(DELIMITER, folderPath.path(), key);
    }

    public String path() {
        return this.path;
    }

    public static String getBackLogoPath() {
        return BANK_LOGO.path() + DELIMITER;
    }
}
