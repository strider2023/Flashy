package com.touchmenotapps.flashy.threads.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.touchmenotapps.flashy.dao.LessonsDAO;
import com.touchmenotapps.flashy.dao.enums.Subject;
import com.touchmenotapps.flashy.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by school on 29/8/16.
 */
public class LessonsLoaderTask extends AsyncTaskLoader<List<LessonsDAO>> {

    private NetworkUtils networkUtils;
    private Map<String, Object> params = new HashMap<>();
    private List<LessonsDAO> lessonsDAOs = new ArrayList<>();

    public LessonsLoaderTask(Context context, Map<String, Object> params) {
        super(context);
        this.networkUtils = new NetworkUtils(context);
        this.params = params;
    }

    @Override
    public List<LessonsDAO> loadInBackground() {
        this.lessonsDAOs.clear();

        LessonsDAO lessonsDAO = new LessonsDAO(getContext());
        lessonsDAO.setSubjectName("Chemistry");
        lessonsDAO.setChapterName("Structure and bonding");
        lessonsDAO.setRevisionTime("5 Mins - Revision Time");
        lessonsDAO.setSubject(Subject.CHEMISTRY);
        lessonsDAO.setComplete(false);
        lessonsDAOs.add(lessonsDAO);

        lessonsDAO = new LessonsDAO(getContext());
        lessonsDAO.setSubjectName("Biology");
        lessonsDAO.setChapterName("What is human anatomy and physiology?");
        lessonsDAO.setRevisionTime("15 Mins - Revision Time");
        lessonsDAO.setSubject(Subject.BIOLOGY);
        lessonsDAO.setComplete(true);
        lessonsDAOs.add(lessonsDAO);

        lessonsDAO = new LessonsDAO(getContext());
        lessonsDAO.setSubjectName("Chemistry");
        lessonsDAO.setChapterName("Alkanes, cycloalkanes, and functional groups.");
        lessonsDAO.setRevisionTime("15 Mins - Revision Time");
        lessonsDAO.setSubject(Subject.CHEMISTRY);
        lessonsDAO.setComplete(true);
        lessonsDAOs.add(lessonsDAO);

        lessonsDAO = new LessonsDAO(getContext());
        lessonsDAO.setSubjectName("Chemistry");
        lessonsDAO.setChapterName("Substitution and elimination reactions");
        lessonsDAO.setRevisionTime("10 Mins - Revision Time");
        lessonsDAO.setSubject(Subject.CHEMISTRY);
        lessonsDAO.setComplete(false);
        lessonsDAOs.add(lessonsDAO);

        lessonsDAO = new LessonsDAO(getContext());
        lessonsDAO.setSubjectName("Chemistry");
        lessonsDAO.setChapterName("Structure and bonding");
        lessonsDAO.setRevisionTime("5 Mins - Revision Time");
        lessonsDAO.setSubject(Subject.CHEMISTRY);
        lessonsDAO.setComplete(false);
        lessonsDAOs.add(lessonsDAO);

        lessonsDAO = new LessonsDAO(getContext());
        lessonsDAO.setSubjectName("Biology");
        lessonsDAO.setChapterName("What is human anatomy and physiology?");
        lessonsDAO.setRevisionTime("15 Mins - Revision Time");
        lessonsDAO.setSubject(Subject.BIOLOGY);
        lessonsDAO.setComplete(true);
        lessonsDAOs.add(lessonsDAO);

        lessonsDAO = new LessonsDAO(getContext());
        lessonsDAO.setSubjectName("Chemistry");
        lessonsDAO.setChapterName("Alkanes, cycloalkanes, and functional groups.");
        lessonsDAO.setRevisionTime("15 Mins - Revision Time");
        lessonsDAO.setSubject(Subject.CHEMISTRY);
        lessonsDAO.setComplete(true);
        lessonsDAOs.add(lessonsDAO);

        lessonsDAO = new LessonsDAO(getContext());
        lessonsDAO.setSubjectName("Chemistry");
        lessonsDAO.setChapterName("Substitution and elimination reactions");
        lessonsDAO.setRevisionTime("10 Mins - Revision Time");
        lessonsDAO.setSubject(Subject.CHEMISTRY);
        lessonsDAO.setComplete(false);
        lessonsDAOs.add(lessonsDAO);

        lessonsDAO = new LessonsDAO(getContext());
        lessonsDAO.setSubjectName("Chemistry");
        lessonsDAO.setChapterName("Structure and bonding");
        lessonsDAO.setRevisionTime("5 Mins - Revision Time");
        lessonsDAO.setSubject(Subject.CHEMISTRY);
        lessonsDAO.setComplete(false);
        lessonsDAOs.add(lessonsDAO);

        lessonsDAO = new LessonsDAO(getContext());
        lessonsDAO.setSubjectName("Biology");
        lessonsDAO.setChapterName("What is human anatomy and physiology?");
        lessonsDAO.setRevisionTime("15 Mins - Revision Time");
        lessonsDAO.setSubject(Subject.BIOLOGY);
        lessonsDAO.setComplete(true);
        lessonsDAOs.add(lessonsDAO);

        lessonsDAO = new LessonsDAO(getContext());
        lessonsDAO.setSubjectName("Chemistry");
        lessonsDAO.setChapterName("Alkanes, cycloalkanes, and functional groups.");
        lessonsDAO.setRevisionTime("15 Mins - Revision Time");
        lessonsDAO.setSubject(Subject.CHEMISTRY);
        lessonsDAO.setComplete(true);
        lessonsDAOs.add(lessonsDAO);

        lessonsDAO = new LessonsDAO(getContext());
        lessonsDAO.setSubjectName("Chemistry");
        lessonsDAO.setChapterName("Substitution and elimination reactions");
        lessonsDAO.setRevisionTime("10 Mins - Revision Time");
        lessonsDAO.setSubject(Subject.CHEMISTRY);
        lessonsDAO.setComplete(false);
        lessonsDAOs.add(lessonsDAO);

        lessonsDAO = new LessonsDAO(getContext());
        lessonsDAO.setSubjectName("Chemistry");
        lessonsDAO.setChapterName("Structure and bonding");
        lessonsDAO.setRevisionTime("5 Mins - Revision Time");
        lessonsDAO.setSubject(Subject.CHEMISTRY);
        lessonsDAO.setComplete(false);
        lessonsDAOs.add(lessonsDAO);

        lessonsDAO = new LessonsDAO(getContext());
        lessonsDAO.setSubjectName("Biology");
        lessonsDAO.setChapterName("What is human anatomy and physiology?");
        lessonsDAO.setRevisionTime("15 Mins - Revision Time");
        lessonsDAO.setSubject(Subject.BIOLOGY);
        lessonsDAO.setComplete(true);
        lessonsDAOs.add(lessonsDAO);

        lessonsDAO = new LessonsDAO(getContext());
        lessonsDAO.setSubjectName("Chemistry");
        lessonsDAO.setChapterName("Alkanes, cycloalkanes, and functional groups.");
        lessonsDAO.setRevisionTime("15 Mins - Revision Time");
        lessonsDAO.setSubject(Subject.CHEMISTRY);
        lessonsDAO.setComplete(true);
        lessonsDAOs.add(lessonsDAO);

        lessonsDAO = new LessonsDAO(getContext());
        lessonsDAO.setSubjectName("Chemistry");
        lessonsDAO.setChapterName("Substitution and elimination reactions");
        lessonsDAO.setRevisionTime("10 Mins - Revision Time");
        lessonsDAO.setSubject(Subject.CHEMISTRY);
        lessonsDAO.setComplete(false);
        lessonsDAOs.add(lessonsDAO);

        return lessonsDAOs;
    }
}
