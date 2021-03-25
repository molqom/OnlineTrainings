package by.epam.web.tag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class DateTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpSession session = pageContext.getSession();
        String lang = (String) session.getAttribute("lang");
        SimpleDateFormat ruDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        SimpleDateFormat enDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        try {
            if (lang == null) {
                out.print(enDateFormat.format(new Date()));
                return SKIP_BODY;
            }
            switch (lang) {
                case "ru" :
                case "by" :
                    out.print(ruDateFormat.format(new Date()));
                    break;
                default:
                    out.print(enDateFormat.format(new Date()));
                    break;
            }

        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
