package com.savchenko.controlsystem.readers;

import com.savchenko.controlsystem.config.ProjectPropertiesLoader;
import com.savchenko.controlsystem.models.ControlPoint;
import com.savchenko.controlsystem.models.IncomingTask;
import com.savchenko.controlsystem.models.Lesson;
import lombok.Getter;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
public class DatesReader {
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    private List<IncomingTask> incomingTasks =  new ArrayList<>();
    private List<Lesson> lessons = new ArrayList<>();
    private List<ControlPoint> controlPoints = new ArrayList<>();

    public DatesReader(String group){
        String path = ProjectPropertiesLoader.instance.getProperty("groupsLocation") + group + "/dates";
        File file = new File(path);
        if(!file.canRead()) throw new RuntimeException("Cant get file \"dates\" by path \"" + path + "\"");
        parse(file);
    }

    private void parse(File file){
        try(Scanner scanner = new Scanner(file)) {
            while(scanner.hasNext()){
                String str =scanner.nextLine();
                if(CommentsChecker.check(str)) continue;
                String[] words = str.replaceAll("[\\s]{2,}", " ").trim().split(" ");

                if(words.length == 1) {
                    lessons.add(new Lesson(formatter.parse(words[0])));
                    continue;
                }

                if(words[0].matches("..-..-....")) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for(int i = 1; i < words.length; i++) stringBuilder.append(words[i]).append(" ");
                    controlPoints.add(new ControlPoint(stringBuilder.toString().trim(), formatter.parse(words[0])));
                }
                else incomingTasks.add(new IncomingTask(words[0], formatter.parse(words[1])));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) { throw new RuntimeException("Wrong \"dates\" file format by path \"" + file.getAbsolutePath() + "\""); }
    }
}