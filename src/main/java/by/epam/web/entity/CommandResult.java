package by.epam.web.entity;


public class CommandResult {
    private final String url;
    private final boolean isRedirect;


    private CommandResult(String page, boolean isRedirect) {
        this.url = page;
        this.isRedirect = isRedirect;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page, false);
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
