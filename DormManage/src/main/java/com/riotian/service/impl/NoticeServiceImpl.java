package com.riotian.service.impl;

import com.riotian.dao.NoticeDAO;
import com.riotian.dao.impl.NoticeDAOImpl;
import com.riotian.pojo.Notice;
import com.riotian.service.NoticeService;

import java.io.Serializable;
import java.util.Map;

/**
 * Notice模块的Service层（业务层）的具体实现类，对NoticeService接口中定义的抽象方法作出具体的功能实现
 */
public class NoticeServiceImpl implements NoticeService {

    public void add(Notice vo) {
        NoticeDAO noticeDAO = new NoticeDAOImpl();
        noticeDAO.add(vo);
    }


    public void delete(long id) {
        NoticeDAO noticeDAO = new NoticeDAOImpl();
        noticeDAO.delete(id);
    }


    public void update(Notice vo) {
        NoticeDAO noticeDAO = new NoticeDAOImpl();
        noticeDAO.update(vo);
    }


    public Notice get(Serializable id) {
        NoticeDAO noticeDAO = new NoticeDAOImpl();
        return noticeDAO.get(id);
    }


    public Map<String, Object> list(Map<String, Object> params) {
        NoticeDAO noticeDAO = new NoticeDAOImpl();
        return noticeDAO.list(params);
    }
}
