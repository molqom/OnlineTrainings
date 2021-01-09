package by.epam.web.entity;

import by.epam.web.enums.Url;

public class CommandResult {
    private final String url;
    private final boolean isRedirect;

    private CommandResult(Url page, boolean isRedirect) {
        this.url = page.getUrl();
        this.isRedirect = isRedirect;
    }
    private CommandResult(String page, boolean isRedirect) {
        this.url = page;
        this.isRedirect = isRedirect;
    }

    public static CommandResult forward(Url page) {
        return new CommandResult(page, false);
    }

    public static CommandResult redirect(Url page) {
        return new CommandResult(page, true);
    }
    public static CommandResult redirect(String page) {
        return new CommandResult(page, true);
    }

    public String getUrl() {
        return url;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}
