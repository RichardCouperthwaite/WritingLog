package edu.tamu.richardcouperthwaite.writinglog.models;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static edu.tamu.richardcouperthwaite.writinglog.models.Stats.getDayIndices;

public class FileIO {
//This class needs to have methods that can handle input and output to three files
//The three files are:
//1) projectlist file (JSON)
//2) stats file (JSON)
//3) alldata file (csv)
//
//The projectlist file will contain a list of all projects that have a structure the same
//and the Project class. The input and ouput for this file needs to send back a list of all the
//projects
//
//the stats file will contain the data for the current and previous weeks and month. Will need to
//be able to input and output data to this file as sessions are completed. only need to get the data
//when the stats tab is opened.
//
//The alldata file needs to be a csv type file (for easy export) that records every session in terms
//of start time, end time, date, project, and final comment. could also consider a comment on how the
//session went. This will just record all data at the end of each session and add a new line to the end
//of the file. No output from this file will be necessary at this time.

    public static ArrayList<Project> getProjectDetails(Context context) {
        ArrayList<Project> Projects = new ArrayList<Project>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context, "project_list.json"));
            JSONArray projArray = obj.getJSONArray("projects");
            for (int i = 0; i < projArray.length(); i++) {
                JSONObject projDetail = projArray.getJSONObject(i);
                if (projDetail.getInt("Archive") == 0) {
                    String id = projDetail.getString("id");
                    String title = projDetail.getString("Title");
                    String description = projDetail.getString("Description");
                    String lastcomment = projDetail.getString("LastComment");
                    int time = projDetail.getInt("Time");
                    String date = projDetail.getString("DueDate");
                    Project newProj = new Project(id, title, description, lastcomment, time, date);
                    Projects.add(newProj);
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        if (Projects.isEmpty()) {
            Projects.add(new Project("0", "Sample Project", "Sample Project", "Last Comment", 0, "01/01/1901"));
        }
        return Projects;
    }

    public static ArrayList<String> getStatisticDetails(Context context) {
        ArrayList<String> stats = new ArrayList<String>();
        try {
            JSONObject statDetail = new JSONObject(loadJSONFromAsset(context, "statistics.json"));
            stats.add(statDetail.getString("currentweektime"));
            stats.add(statDetail.getString("currentweekdays"));
            stats.add(statDetail.getString("prevweektime"));
            stats.add(statDetail.getString("prevweekdays"));
            stats.add(statDetail.getString("currentmonthtime"));
            stats.add(statDetail.getString("currentmonthdays"));
            stats.add(statDetail.getString("prevmonthtime"));
            stats.add(statDetail.getString("prevmonthdays"));
            stats.add(statDetail.getString("weekstartdate"));
            stats.add(statDetail.getString("monthstartdate"));
            stats.add(statDetail.getString("weekdayList"));
            stats.add(statDetail.getString("monthdayList"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return stats;
    }

    public static String loadJSONFromAsset(Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static void saveSessionData(String summary, Context context) {
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput("logData.csv", Context.MODE_APPEND);
            outputStream.write(summary.getBytes());
            outputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void saveStats(int SessionTime, String date, Context context) {
        ArrayList<String> stats = updateStatistics(SessionTime, date, context);
        JSONObject statDetails = new JSONObject();
        try {
            statDetails.put("currentweektime", stats.get(0));
            statDetails.put("currentweekdays", stats.get(1));
            statDetails.put("prevweektime", stats.get(2));
            statDetails.put("prevweekdays", stats.get(3));
            statDetails.put("currentmonthtime", stats.get(4));
            statDetails.put("currentmonthdays", stats.get(5));
            statDetails.put("prevmonthtime", stats.get(6));
            statDetails.put("prevmonthdays", stats.get(7));
            statDetails.put("weekstartdate", stats.get(8));
            statDetails.put("monthstartdate", stats.get(9));
            statDetails.put("weekdayList", stats.get(10));
            statDetails.put("monthdayList", stats.get(11));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    public static ArrayList<String> updateStatistics(int SessionTime, String date, Context context) {
        String currentDate[] = date.split("/");

        ArrayList<String> stats = getStatisticDetails(context);
        ArrayList<Integer> dayindices = getDayIndices(date, stats.get(8), stats.get(9));
        // if the current date is not in the month set by month start date, adjust the lists
        if (dayindices.get(0) == 2) {
            stats.set(4, String.format("%d", SessionTime));
            stats.set(6, "0");
            stats.set(11, "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
            stats.set(7, String.format("0/%d", dayindices.get(8)));
        } else if (dayindices.get(0) == 1) {
            stats.set(6, stats.get(4));
            stats.set(4, String.format("%d", SessionTime));
            stats.set(11, "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
            stats.set(7, stats.get(5));
        } else {
            int currenttime = Integer.getInteger(stats.get(4));
            stats.set(4, String.format("%d", SessionTime + currenttime));
        }
        // if the current date is not in the week set by week start date, adjust the lists
        if (dayindices.get(2) == 2) {
            stats.set(0, String.format("%d", SessionTime));
            stats.set(2, "0");
            stats.set(10, "0,0,0,0,0,0,0");
            stats.set(3, "0/7");
        } else if (dayindices.get(2) == 1) {
            stats.set(2, stats.get(4));
            stats.set(0, String.format("%d", SessionTime));
            stats.set(10, "0,0,0,0,0,0,0");
            stats.set(3, stats.get(1));
        } else {
            int currenttime = Integer.getInteger(stats.get(0));
            stats.set(0, String.format("%d", SessionTime + currenttime));
        }
        // add the current date to the list of days in the week and month
        String wdays[] = stats.get(10).split(",");
        String mdays[] = stats.get(11).split(",");
        if (wdays[dayindices.get(3)].equals("0")) {
            wdays[dayindices.get(3)] = "1";
        }
        if (mdays[dayindices.get(1)].equals("0")) {
            mdays[dayindices.get(1)] = "1";
        }
        int countweek = 0;
        int countmonth = 0;
        for (int i=0; i<7; i++) {
            if (wdays[i].equals("1")) {
                countweek++;
            }
        }
        for (int i=0; i<31; i++) {
            if (mdays[i].equals("1")) {
                countmonth++;
            }
        }
        stats.set(1, String.format("%d/7",countweek));
        stats.set(5, String.format("%d/%d", countmonth, dayindices.get(7)));
        stats.set(8, String.format("%d/%d/%d", dayindices.get(5), dayindices.get(4), dayindices.get(6)));
        stats.set(9, String.format("1/%s/%s", currentDate[0], currentDate[2]));

        return stats;
    }
}
