package Runner;



import Constants.ApplicationConstant;
import JavaUtils.GenericJavaUtilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;
import org.zeroturnaround.zip.ZipUtil;
import org.zeroturnaround.zip.commons.FileUtils;

import java.io.File;
import java.io.IOException;


public class Post_Build_Runner {

    @AfterSuite
    public void extent_report_file_compressor() throws IOException {

        File file = new File(System.getProperty("user.dir")+File.separator+"extent-reports"+File.separator+"jenkins_current_report");
        FileUtils.deleteDirectory(file);
        file.mkdir();
        ZipUtil.pack(new File(System.getProperty("user.dir")+File.separator+"extent-reports"+File.separator
                +"report_as_on "+ApplicationConstant.current_time_stamp+File.separator),
                new File(System.getProperty("user.dir")+File.separator+"extent-reports"+File.separator
                        +"jenkins_current_report"+File.separator+"report_as_on "+ApplicationConstant.current_time_stamp+".zip"));

    }
}
