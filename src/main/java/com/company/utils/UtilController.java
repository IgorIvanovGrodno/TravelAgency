package com.company.utils;

import org.springframework.beans.support.PagedListHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * This class is util class for controllers.
 *
 * @author Igor Ivanov
 */
public class UtilController
{
    /**
     * This method receives HTTP request, page, name of attribute. It takes list holder from session and set page.
     *
     * @param request               - HTTP request.
     * @param page                  - page.
     * @param attributeNameOfHolder - name of attribute.
     */
    public static void pagination(HttpServletRequest request, String page, String attributeNameOfHolder)
    {
        PagedListHolder<?> pagedListHolder;
        if (page.equals("prev"))
        {
            pagedListHolder = (PagedListHolder<?>) request.getSession().getAttribute(attributeNameOfHolder);
            pagedListHolder.previousPage();
        }
        else if (page.equals("next"))
        {
            pagedListHolder = (PagedListHolder<?>) request.getSession().getAttribute(attributeNameOfHolder);
            pagedListHolder.nextPage();
        }
        else
        {
            int pageNum = Integer.parseInt(page);
            pagedListHolder = (PagedListHolder<?>) request.getSession().getAttribute(attributeNameOfHolder);
            pagedListHolder.setPage(pageNum - 1);
        }
    }
}
