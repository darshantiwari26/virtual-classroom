import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    static final File DATA_DIR = new File("data");
    static final File TEACHERS = new File(DATA_DIR, "teachers.csv");
    static final File STUDENTS = new File(DATA_DIR, "students.csv");
    static final File NOTICES_DIR = new File(DATA_DIR, "notices");
    static final File UPLOADS_DIR = new File("uploads");

    public static void init() {
        if (!DATA_DIR.exists()) DATA_DIR.mkdirs();
        if (!NOTICES_DIR.exists()) NOTICES_DIR.mkdirs();
        if (!UPLOADS_DIR.exists()) UPLOADS_DIR.mkdirs();
        try {
            if (!TEACHERS.exists()) {
                TEACHERS.createNewFile();
                try (PrintWriter pw = new PrintWriter(new FileWriter(TEACHERS))) {
                    pw.println("name,password,codes");
                
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
            
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
} else {
                migrateTeachersIfNeeded();
            
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
            if (!STUDENTS.exists()) {
                STUDENTS.createNewFile();
                try (PrintWriter pw = new PrintWriter(new FileWriter(STUDENTS))) {
                    pw.println("name,password,codes");
                
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
            
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
} else {
                migrateStudentsIfNeeded();
            
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
        
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
} catch (IOException e) { e.printStackTrace(); 
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}

    private static void migrateTeachersIfNeeded() {
        try {
            List<String> lines = Files.readAllLines(TEACHERS.toPath());
            if (lines.isEmpty()) return;
            String header = lines.get(0).trim();
            if (header.equals("name,password,code")) {
                // convert third value to codes
                for (int i=1;i<lines.size();i++) {
                    String[] p = lines.get(i).split(",", -1);
                    if (p.length>=3) lines.set(i, p[0]+","+p[1]+","+p[2]);
                
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
                lines.set(0, "name,password,codes");
                Files.write(TEACHERS.toPath(), lines);
            
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
        
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
} catch (IOException ignored) {
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}

    private static void migrateStudentsIfNeeded() {
        try {
            List<String> lines = Files.readAllLines(STUDENTS.toPath());
            if (lines.isEmpty()) return;
            String header = lines.get(0).trim();
            if (header.equals("name,password,joined_code")) {
                lines.set(0, "name,password,codes");
                Files.write(STUDENTS.toPath(), lines);
            
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
        
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
} catch (IOException ignored) {
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}

    static boolean isSixDigit(String code) {
        return code != null && code.matches("\\d{6
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}");
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}

    static boolean codeExists(String code) throws IOException {
        // check across all teacher codes for uniqueness
        for (String[] t : readCSV(TEACHERS)) {
            if (t.length>=3 && t[2]!=null) {
                for (String c : t[2].split(";")) {
                    if (c.trim().equals(code)) return true;
                
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
            
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
        
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
        return false;
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}

    // Teachers multi-code
    static boolean teacherExists(String name) throws IOException {
        for (String[] row : readCSV(TEACHERS)) if (row[0].equalsIgnoreCase(name)) return true;
        return false;
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    static void createTeacher(String name, String password) throws IOException {
        if (teacherExists(name)) throw new IOException("Teacher already exists");
        appendCSV(TEACHERS, new String[]{name,password,""
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
});
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    static String[] teacherAuth(String name, String password) throws IOException {
        for (String[] row : readCSV(TEACHERS))
            if (row[0].equalsIgnoreCase(name) && row[1].equals(password)) return row;
        return null;
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    static java.util.List<String> getTeacherCodes(String name) throws IOException {
        for (String[] row : readCSV(TEACHERS)) {
            if (row[0].equalsIgnoreCase(name)) {
                String codes = row.length>=3 ? row[2] : "";
                java.util.List<String> out = new ArrayList<>();
                if (codes != null && !codes.trim().isEmpty()) {
                    for (String c : codes.split(";")) if (c.trim().length() > 0) out.add(c.trim());
                
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
                return out;
            
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
        
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
        return new ArrayList<>();
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    static void addTeacherClassCode(String name, String code) throws IOException {
        if (!isSixDigit(code)) throw new IOException("Code must be 6 digits.");
        if (codeExists(code)) throw new IOException("This code already exists. Please choose another.");
        java.util.List<String[]> rows = readCSV(TEACHERS);
        for (String[] r : rows) {
            if (r[0].equalsIgnoreCase(name)) {
                String cur = r.length>=3 ? r[2] : "";
                java.util.LinkedHashSet<String> set = new java.util.LinkedHashSet<>();
                if (cur != null && !cur.isEmpty()) for (String c : cur.split(";")) if (!c.isEmpty()) set.add(c);
                set.add(code);
                r[2] = String.join(";", set);
            
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
        
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
        writeCSV(TEACHERS, rows, new String[]{"name","password","codes"
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
});
        ensureClassDirs(code);
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}

    // Students multi-code
    static boolean studentExists(String name) throws IOException {
        for (String[] row : readCSV(STUDENTS)) if (row[0].equalsIgnoreCase(name)) return true;
        return false;
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    static void createStudent(String name, String password) throws IOException {
        if (studentExists(name)) throw new IOException("Student already exists");
        appendCSV(STUDENTS, new String[]{name,password,""
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
});
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    static String[] studentAuth(String name, String password) throws IOException {
        for (String[] row : readCSV(STUDENTS))
            if (row[0].equalsIgnoreCase(name) && row[1].equals(password)) return row;
        return null;
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    static java.util.List<String> getStudentCodes(String name) throws IOException {
        for (String[] row : readCSV(STUDENTS)) {
            if (row[0].equalsIgnoreCase(name)) {
                String codes = row.length >= 3 ? row[2] : "";
                java.util.List<String> out = new ArrayList<>();
                if (codes != null && !codes.trim().isEmpty()) {
                    for (String c : codes.split(";")) if (c.trim().length() > 0) out.add(c.trim());
                
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
                return out;
            
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
        
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
        return new ArrayList<>();
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    static void addStudentClassCode(String name, String code) throws IOException {
        if (!isSixDigit(code)) throw new IOException("Code must be 6 digits.");
        // student can only join existing teacher codes
        if (!codeExists(code)) throw new IOException("Invalid code: no teacher owns this code.");
        java.util.List<String[]> rows = readCSV(STUDENTS);
        for (String[] r : rows) {
            if (r[0].equalsIgnoreCase(name)) {
                String cur = r.length>=3 ? r[2] : "";
                java.util.LinkedHashSet<String> set = new java.util.LinkedHashSet<>();
                if (cur != null && !cur.isEmpty()) for (String c : cur.split(";")) if (!c.isEmpty()) set.add(c);
                set.add(code);
                r[2] = String.join(";", set);
            
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
        
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
        writeCSV(STUDENTS, rows, new String[]{"name","password","codes"
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
});
        ensureClassDirs(code);
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}

    static File noticeFile(String code) { return new File(NOTICES_DIR, code + ".txt"); 
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    static String readNotice(String code) {
        File f = noticeFile(code);
        if (!f.exists()) return "(No notices yet)";
        try { return new String(Files.readAllBytes(f.toPath())); 
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
        catch(IOException e){ return "(Error reading notice)"; 
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    static void writeNotice(String code, String text) {
        try { Files.write(noticeFile(code).toPath(), text.getBytes()); 
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
        catch(IOException e){ e.printStackTrace(); 
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}

    static void ensureClassDirs(String code) {
        new File(UPLOADS_DIR, code + "/assignments").mkdirs();
        new File(UPLOADS_DIR, code + "/videos").mkdirs();
        new File(UPLOADS_DIR, code + "/submissions").mkdirs();
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    static File studentSubmissionDir(String code, String student) {
        File dir = new File(UPLOADS_DIR, code + "/submissions/" + student);
        dir.mkdirs();
        return dir;
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}

    static java.util.List<File> listFiles(File dir) {
        File[] arr = dir.listFiles();
        java.util.List<File> out = new ArrayList<>();
        if (arr != null) for (File f : arr) if (f.isFile()) out.add(f);
        return out;
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}

    // CSV
    static java.util.List<String[]> readCSV(File f) throws IOException {
        java.util.List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line; boolean header = true;
            while ((line = br.readLine()) != null) {
                if (header) { header = false; continue; 
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
                String[] parts = line.split(",", -1);
                if (parts.length > 0 && parts[0].trim().length() > 0) rows.add(parts);
            
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
        
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
        return rows;
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    static void appendCSV(File f, String[] row) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(f, true))) {
            pw.println(String.join(",", row));
        
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    static void writeCSV(File f, java.util.List<String[]> rows, String[] header) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
            pw.println(String.join(",", header));
            for (String[] r : rows) pw.println(String.join(",", r));
        
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
    
    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}

    public static void markAttendance(String user, String code) {
        try {
            File f = new File("data/attendance_"+code+".csv");
            java.nio.file.Files.write(f.toPath(), (user + "," + java.time.LocalDate.now() + "\n").getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
        } catch(Exception e){ e.printStackTrace(); }
    }
    
}
