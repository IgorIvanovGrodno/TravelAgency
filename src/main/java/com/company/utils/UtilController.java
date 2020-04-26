package com.company.utils;

import org.springframework.beans.support.PagedListHolder;

import javax.servlet.http.HttpServletRequest;

public class UtilController {
    public static void pagination(HttpServletRequest request, String page, String attributeNameOfHolder){
        PagedListHolder<?> pagedListHolder;
        if(page.equals("prev")) {
            pagedListHolder = (PagedListHolder<?>) request.getSession().getAttribute(attributeNameOfHolder);
            pagedListHolder.previousPage();
        }else if(page.equals("next")) {
            pagedListHolder = (PagedListHolder<?>) request.getSession().getAttribute(attributeNameOfHolder);
            pagedListHolder.nextPage();
        }else {
            int pageNum = Integer.parseInt(page);
            pagedListHolder = (PagedListHolder<?>) request.getSession().getAttribute(attributeNameOfHolder);
            pagedListHolder.setPage(pageNum - 1);
        }
    }
}
