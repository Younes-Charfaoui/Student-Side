/*------------------------------------------------------------------------------
 - Copyright (c) 2018. This code was created by Younes Charfaoui in the process of Graduation Project for the year of  2018 , which is about creating a platform  for students and professors to help them in the communication and the get known of the university information and so on.
 -----------------------------------------------------------------------------*/

package com.ibnkhaldoun.studentside.networking.utilities;

import android.util.SparseArray;

import com.ibnkhaldoun.studentside.models.Comment;
import com.ibnkhaldoun.studentside.models.Display;
import com.ibnkhaldoun.studentside.models.MailProfessor;
import com.ibnkhaldoun.studentside.models.MarkItem;
import com.ibnkhaldoun.studentside.models.Message;
import com.ibnkhaldoun.studentside.models.Notification;
import com.ibnkhaldoun.studentside.models.Professor;
import com.ibnkhaldoun.studentside.models.ProfessorInfo;
import com.ibnkhaldoun.studentside.models.Saved;
import com.ibnkhaldoun.studentside.models.Schedule;
import com.ibnkhaldoun.studentside.models.ScheduleItem;
import com.ibnkhaldoun.studentside.models.Student;
import com.ibnkhaldoun.studentside.models.Subject;
import com.ibnkhaldoun.studentside.networking.models.LoginResponse;
import com.ibnkhaldoun.studentside.networking.models.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.ibnkhaldoun.studentside.networking.models.Response.JSON_EXCEPTION;
import static com.ibnkhaldoun.studentside.networking.models.Response.RESPONSE_SUCCESS;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_COMMENT_COMMENT;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_COMMENT_DATE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_COMMENT_ID_COMMENT;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_COMMENT_ID_PERSON;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_COMMENT_ID_POST;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_COMMENT_PERSON_NAME;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_DAY_SCHEDULE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_HOUR_SCHEDULE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_MAIL_DATE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_MAIL_ID;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_MAIL_MESSAGE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_MAIL_PROFESSOR;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_MAIL_SENDER;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_MAIL_SUBJECT;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_MARK_COURSE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_MARK_TD;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_MARK_TP;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_NAME_SCHEDULE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_NOTIFICATION_SEEN;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_PLACE_SCHEDULE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_POST_DATE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_POST_FILE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_POST_ID;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_POST_PROFESSOR;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_POST_SAVED;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_POST_SUBJECT;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_POST_TEXT;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_POST_TYPE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_PROFESSOR_DEGREE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_PROFESSOR_EMAIL;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_PROFESSOR_FIRST_NAME;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_PROFESSOR_ID;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_PROFESSOR_LAST_NAME;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_PROFESSOR_NAME;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_STUDENT_AVERAGE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_STUDENT_CONFIRMED;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_STUDENT_EMAIL;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_STUDENT_FIRST_NAME;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_STUDENT_FULL_NAME_S;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_STUDENT_GROUP;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_STUDENT_ID;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_STUDENT_LAST_NAME;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_STUDENT_LEVEL;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_STUDENT_SECTION;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_SUBJECT_COEFFICIENT;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_SUBJECT_CONTENT;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_SUBJECT_CREDIT;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_SUBJECT_DATA;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_SUBJECT_ID;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_SUBJECT_LEVEL;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_SUBJECT_PROFESSOR_COURSE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_SUBJECT_PROFESSOR_TD;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_SUBJECT_PROFESSOR_TP;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_SUBJECT_SHORT_TITLE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_SUBJECT_SUMMARY;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_SUBJECT_TITLE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_SUBJECT_UNITY_TYPE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_TITLE_SCHEDULE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.JSON_TYPE_SCHEDULE;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.KEY_JSON_DATA;
import static com.ibnkhaldoun.studentside.providers.KeyDataProvider.KEY_JSON_STATUS;

/**
 * this class will make the role of doing the parse
 * process for all the responses.
 */

public class JsonUtilities {

    public static LoginResponse getLoginResponse(String jsonText) {
        try {
            JSONObject root = new JSONObject(jsonText);

            int status = root.getInt(KEY_JSON_STATUS);

            LoginResponse response = new LoginResponse(status);

            if (status == RESPONSE_SUCCESS) {

                JSONObject data = root.getJSONObject(KEY_JSON_DATA);

                if (data.has(JSON_STUDENT_ID)) {
                    Student student = new Student();
                    student.setId(data.getString(JSON_STUDENT_ID));
                    student.setFirstName(data.getString(JSON_STUDENT_FIRST_NAME));
                    student.setLastName(data.getString(JSON_STUDENT_LAST_NAME));
                    student.setAverage(Float.parseFloat(data.getString(JSON_STUDENT_AVERAGE)));
                    student.setEmail(data.getString(JSON_STUDENT_EMAIL));
                    student.setLevel(Integer.parseInt(data.getString(JSON_STUDENT_LEVEL)));
                    student.setGroup(Integer.parseInt(data.getString(JSON_STUDENT_GROUP)));
                    student.setSection(Integer.parseInt(data.getString(JSON_STUDENT_SECTION)));
                    student.setConfirmed(data.getString(JSON_STUDENT_CONFIRMED).equals("1"));
                    response.setIsStudent(true);
                    response.setStudent(student);
                } else {
                    Professor professor = new Professor();
                    professor.setId(data.getString(JSON_PROFESSOR_ID));
                    professor.setFirstName(data.getString(JSON_PROFESSOR_FIRST_NAME));
                    professor.setLastName(data.getString(JSON_PROFESSOR_LAST_NAME));
                    professor.setEmail(data.getString(JSON_PROFESSOR_EMAIL));
                    professor.setDegree(data.getString(JSON_PROFESSOR_DEGREE));
                    response.setProfessor(professor);
                    response.setIsStudent(false);
                }
            }
            return response;
        } catch (JSONException e) {
            e.printStackTrace();
            return new LoginResponse(JSON_EXCEPTION);
        }
    }

    public static Response getSignUpResponse(String jsonText) {
        try {
            JSONObject root = new JSONObject(jsonText);
            int status = root.getInt(KEY_JSON_STATUS);

            return new Response(status);
        } catch (JSONException e) {

            e.printStackTrace();
            return new Response(JSON_EXCEPTION);
        }
    }

    public static ArrayList<Subject> getSubjectList(String response) {
        try {
            JSONObject root = new JSONObject(response);
            int status = root.getInt(KEY_JSON_STATUS);
            if (status != 200) return null;
            JSONArray subjects = root.getJSONArray(JSON_SUBJECT_DATA);
            return getSubjectFromJsonArray(subjects);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Response getForgetPasswordResponse(String response) {
        try {
            return new Response(Integer.
                    parseInt(new JSONObject(response)
                            .getString(KEY_JSON_STATUS)));
        } catch (JSONException e) {

            return new Response(Response.JSON_EXCEPTION);
        }
    }

    public static ArrayList<MarkItem> getMarkList(String response) {
        ArrayList<MarkItem> marks = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(response);
            if (root.getInt(KEY_JSON_STATUS) != 200) throw new JSONException("was not 200");
            JSONArray objects = root.getJSONArray(KEY_JSON_DATA);
            for (int i = 0; i < objects.length(); i++) {
                long idSubject = objects.getJSONObject(i).getLong(JSON_SUBJECT_ID);
                String tdMark = objects.getJSONObject(i).getString(JSON_MARK_TD);
                String course = objects.getJSONObject(i).getString(JSON_MARK_COURSE);
                String tpMark = objects.getJSONObject(i).getString(JSON_MARK_TP);
                String title = objects.getJSONObject(i).getString(JSON_SUBJECT_TITLE);
                String shortTitle = objects.getJSONObject(i).getString(JSON_SUBJECT_SHORT_TITLE);


                float td, tp, exam;
                try {
                    td = Float.parseFloat(tdMark);
                } catch (NumberFormatException e) {
                    td = -1f;
                }
                try {
                    tp = Float.parseFloat(tpMark);
                } catch (NumberFormatException e) {
                    tp = -1f;
                }
                try {
                    exam = Float.parseFloat(course);
                } catch (NumberFormatException e) {
                    exam = -1f;
                }
                marks.add(new MarkItem(title, shortTitle, exam, td, tp, idSubject));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return marks;
    }

    public static ArrayList<Message> getMailList(String response) {
        ArrayList<Message> messages = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(response);
            if (root.getInt(KEY_JSON_STATUS) != 200) throw new JSONException("no Success messages");
            JSONArray objects = root.getJSONArray(KEY_JSON_DATA);
            for (int i = 0; i < objects.length(); i++) {
                JSONObject object = objects.getJSONObject(i);
                long id = object.getLong(JSON_MAIL_ID);
                long idStudent = object.getLong(JSON_STUDENT_ID);
                long idProfessor = object.getLong(JSON_PROFESSOR_ID);
                String message = object.getString(JSON_MAIL_MESSAGE);
                String subject = object.getString(JSON_MAIL_SUBJECT);
                String date = object.getString(JSON_MAIL_DATE);
                String professor = object.getString(JSON_MAIL_PROFESSOR);
                boolean sender = object.getInt(JSON_MAIL_SENDER) == 1;
                String student = object.getString(JSON_STUDENT_FULL_NAME_S);
                messages.add(new Message(id, idStudent, idProfessor, sender, subject, message, date, professor, student));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return messages;
    }

    private static ArrayList<Subject> getSubjectFromJsonArray(JSONArray array) throws JSONException {
        ArrayList<Subject> subjects = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            String title = array.getJSONObject(i).getString(JSON_SUBJECT_TITLE);
            String short_title = array.getJSONObject(i).getString(JSON_SUBJECT_SHORT_TITLE);
            String coefficient = array.getJSONObject(i).getString(JSON_SUBJECT_COEFFICIENT);

            String content = array.getJSONObject(i).getString(JSON_SUBJECT_CONTENT);
            String summary = array.getJSONObject(i).getString(JSON_SUBJECT_SUMMARY);
            String credit = array.getJSONObject(i).getString(JSON_SUBJECT_CREDIT);
            int unityType = array.getJSONObject(i).getInt(JSON_SUBJECT_UNITY_TYPE);

            int level = array.getJSONObject(i).getInt(JSON_SUBJECT_LEVEL);
            Subject subject = new Subject(title, short_title, summary,
                    content, credit, coefficient, unityType, level);
            if (array.getJSONObject(i).has(JSON_SUBJECT_PROFESSOR_COURSE)) {
                subject.setCourseProfessor(array.getJSONObject(i).getString(JSON_SUBJECT_PROFESSOR_COURSE));
            }
            if (array.getJSONObject(i).has(JSON_SUBJECT_PROFESSOR_TD)) {
                subject.setTdProfessor(array.getJSONObject(i).getString(JSON_SUBJECT_PROFESSOR_TD));
            }
            if (array.getJSONObject(i).has(JSON_SUBJECT_PROFESSOR_TP)) {
                subject.setTpProfessor(array.getJSONObject(i).getString(JSON_SUBJECT_PROFESSOR_TP));
            }
            subjects.add(subject);
        }
        return subjects;
    }

    public static ArrayList<Saved> getSavedList(String response) throws JSONException {
        JSONObject root = new JSONObject(response);
        if (root.getInt(KEY_JSON_STATUS) != 200) throw new JSONException("Error in code");

        ArrayList<Saved> saves = new ArrayList<>();
        JSONArray data = root.getJSONArray(KEY_JSON_DATA);
        for (int i = 0; i < data.length(); i++) {
            JSONObject saved = data.getJSONObject(i);
            long id = saved.getLong(JSON_POST_ID);
            String date = saved.getString(JSON_POST_DATE);
            String text = saved.getString(JSON_POST_TEXT);
            String professor = saved.getString(JSON_POST_PROFESSOR);
            String file = saved.getString(JSON_POST_FILE);
            String subject = saved.getString(JSON_POST_SUBJECT);
            int type = saved.getInt(JSON_POST_TYPE);
            Saved save = new Saved(id, professor, text, date);
            save.setFilePath(file);
            save.setSubjectTitle(subject);
            save.setType(type);
            saves.add(save);
        }
        return saves;
    }

    public static SparseArray<Schedule> getSchedulesList(String response) throws JSONException {
        SparseArray<Schedule> schedules = new SparseArray<>();
        JSONObject root = new JSONObject(response);

        int status = root.getInt(KEY_JSON_STATUS);
        if (status != 200) throw new JSONException("Code was not 200");
        JSONArray data = root.getJSONArray(JSON_SUBJECT_DATA);
        int level = root.getInt(JSON_STUDENT_LEVEL),
                section = root.getInt(JSON_STUDENT_SECTION),
                group = root.getInt(JSON_STUDENT_GROUP);
        for (int i = 0; i < data.length(); i++) {
            JSONObject object = data.getJSONObject(i);
            int day = object.getInt(JSON_DAY_SCHEDULE);
            Schedule schedule = schedules.get(day, null) != null ? schedules.get(day)
                    : new Schedule(day, level, section, group);
            int time = object.getInt(JSON_HOUR_SCHEDULE);
            String place = object.getString(JSON_PLACE_SCHEDULE);
            String subject = object.getString(JSON_TITLE_SCHEDULE);
            String professor = object.getString(JSON_NAME_SCHEDULE);
            int type = object.getInt(JSON_TYPE_SCHEDULE);
            ScheduleItem item = new ScheduleItem(time, place, subject, professor, type);
            schedule.getScheduleItemList().add(item);
            schedules.put(day, schedule);
        }
        return schedules;
    }

    public static ArrayList<Comment> getCommentsList(String response) {
        ArrayList<Comment> commentList = new ArrayList<>();
        try {

            JSONObject root = new JSONObject(response);
            if (root.getInt(KEY_JSON_STATUS) != 200) throw new JSONException("Status was not 200");
            JSONArray object = root.getJSONArray(KEY_JSON_DATA);
            for (int i = 0; i < object.length(); i++) {
                long idComment = object.getJSONObject(i).getLong(JSON_COMMENT_ID_COMMENT);
                long idPerson = object.getJSONObject(i).getLong(JSON_COMMENT_ID_PERSON);
                long idPost = object.getJSONObject(i).getLong(JSON_COMMENT_ID_POST);
                String personName = object.getJSONObject(i).getString(JSON_COMMENT_PERSON_NAME);
                String date = object.getJSONObject(i).getString(JSON_COMMENT_DATE);
                String comment = object.getJSONObject(i).getString(JSON_COMMENT_COMMENT);
                commentList.add(new Comment(personName, comment, date, idComment, idPost, idPerson));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return commentList;
    }

    public static ArrayList<Notification> getNotificationList(String response) throws JSONException {
        ArrayList<Notification> notificationList = new ArrayList<>();
        JSONObject root = new JSONObject(response);
        if (root.getInt(KEY_JSON_STATUS) != 200) throw new JSONException("Error code");
        JSONArray data = root.getJSONArray(KEY_JSON_DATA);
        for (int i = 0; i < data.length(); i++) {
            JSONObject object = data.getJSONObject(i);
            int type = object.getInt(JSON_POST_TYPE);
            long id = object.getInt(JSON_POST_ID);
            boolean seen = object.getInt(JSON_NOTIFICATION_SEEN) == 1;
            String professor = object.getString(JSON_POST_PROFESSOR);
            String subjectTitle = object.getString(JSON_POST_SUBJECT);
            String date = object.getString(JSON_POST_DATE);
            notificationList.add(new Notification(professor, date, subjectTitle, type, id, seen));
        }
        return notificationList;
    }

    public static ArrayList<Display> getDisplaysList(String response) {
        ArrayList<Display> displays = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(response);
            if (root.getInt(KEY_JSON_STATUS) != 200) throw new JSONException("Code was not 200");
            JSONArray data = root.getJSONArray(KEY_JSON_DATA);
            for (int i = 0; i < data.length(); i++) {
                JSONObject object = data.getJSONObject(i);
                long id = object.getLong(JSON_POST_ID);
                int type = object.getInt(JSON_POST_TYPE);
                String text = object.getString(JSON_POST_TEXT);
                String file = object.getString(JSON_POST_FILE);
                String date = object.getString(JSON_POST_DATE);
                String professor = object.getString(JSON_POST_PROFESSOR);
                String subject = object.getString(JSON_POST_SUBJECT);
                boolean saved = object.getInt(JSON_POST_SAVED) == 1;
                displays.add(new Display(id, date, professor, text, file, subject, type, saved));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return displays;
    }

    public static ArrayList<Display> getDisplaysListProfessor(String response) {
        ArrayList<Display> displays = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(response);
            if (root.getInt(KEY_JSON_STATUS) != 200) throw new JSONException("Code was not 200");
            JSONArray data = root.getJSONArray(KEY_JSON_DATA);
            for (int i = 0; i < data.length(); i++) {
                JSONObject object = data.getJSONObject(i);
                long id = object.getLong(JSON_POST_ID);
                int type = object.getInt(JSON_POST_TYPE);
                String text = object.getString(JSON_POST_TEXT);
                String file = object.getString(JSON_POST_FILE);
                String date = object.getString(JSON_POST_DATE);
                String subject = object.getString(JSON_POST_SUBJECT);
                displays.add(new Display(id, date, null, text, file, subject, type, false));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return displays;
    }

    public static int getStatusCode(String response) throws JSONException {
        JSONObject object = new JSONObject(response);
        return object.getInt(KEY_JSON_STATUS);
    }

    public static ArrayList<MailProfessor> getProfessorList(String data) {
        ArrayList<MailProfessor> professors = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(data);
            JSONArray objects = root.getJSONArray(KEY_JSON_DATA);
            for (int i = 0; i < objects.length(); i++) {
                String id = objects.getJSONObject(i).getString(JSON_PROFESSOR_ID);
                String name = objects.getJSONObject(i).getString(JSON_PROFESSOR_NAME);
                professors.add(new MailProfessor(id, name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return professors;
    }

    public static ArrayList<ProfessorInfo> getProfessorInfo(String response) {
        ArrayList<ProfessorInfo> professorInfo = new ArrayList<>();
        SparseArray<ProfessorInfo> mMap = new SparseArray<>();
        try {
            JSONObject root = new JSONObject(response);
            if (root.getInt(KEY_JSON_STATUS) != 200) throw new JSONException("Code was not 200");
            JSONArray data = root.getJSONArray(KEY_JSON_DATA);

            for (int i = 0; i < data.length(); i++) {
                ProfessorInfo info;
                JSONObject object = data.getJSONObject(i);
                int idSubject = object.getInt(JSON_SUBJECT_ID);
                String title = object.getString(JSON_SUBJECT_TITLE);
                int level = object.getInt(JSON_SUBJECT_LEVEL);
                info = mMap.get(idSubject, null) == null ? new ProfessorInfo(String.valueOf(idSubject), title, level, new HashMap<>()) : mMap.get(idSubject);
                int section = object.getInt(JSON_STUDENT_SECTION);
                int group = object.getInt(JSON_STUDENT_GROUP);
                if (info.getSectionAndGroups().containsKey(section)) {
                    info.getSectionAndGroups().get(section).add(group);
                } else {
                    ArrayList<Integer> groupList = new ArrayList<>();
                    groupList.add(group);
                    info.getSectionAndGroups().put(section, groupList);
                }
                mMap.put(idSubject, info);
            }
            for (int i = 0; i < mMap.size(); i++) {
                professorInfo.add(mMap.get(mMap.keyAt(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return professorInfo;
    }
}
