import com.dounine.japi.JapiClient;
import com.dounine.japi.JapiClientStorage;
import com.dounine.japi.JapiClientTransfer;
import com.dounine.japi.core.IProject;
import com.dounine.japi.core.impl.ProjectImpl;

import java.io.IOException;

public class JapiTest {

    public static void main(String[] args) throws IOException {
//        JapiClient.delete(true);
        JapiClient.setPrefixPath("/home/ike/github/goddess-java/");//路径前缀
        JapiClient.setpostfixPath("/src/main/java");
        JapiClient.setProjectJavaPath("modules/announcement/announcement-consumer");//主项目位置
        JapiClient.setActionReletivePath("com/bjike/goddess/announcement/action");//主项目action位置
        JapiClient.setIncludeProjectJavaPath(new String[]{//关联项目
                "modules/announcement/announcement-api",
                "modules/user/user-api",
                "modules/organize/organize-api",
                "modules/contacts/contacts-api",
                "modules/message/message-api",
                "modules/storage/storage-api",
                "common/common-api"
        });
        JapiClient.setFlushServer(true);
        JapiClient.setIncludePackages(new String[]{"com.bjike.goddess"});//可以准确快速搜索
        IProject project = ProjectImpl.init();
        JapiClientStorage japiClientStorage = JapiClientStorage.getInstance();
        japiClientStorage.setProject(project);
        japiClientStorage.autoSaveToDisk();
        new JapiClientTransfer().autoTransfer(japiClientStorage);
    }

}
