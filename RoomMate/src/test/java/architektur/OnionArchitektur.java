package architektur;

import RoomMate.RoomMateApplication;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;
@AnalyzeClasses(packagesOf = RoomMateApplication.class)
public class OnionArchitektur {

        @ArchTest
        ArchRule rule = onionArchitecture()
                .domainModels("RoomMate.domain..")
                .domainServices("RoomMate.service..")
                .applicationServices("RoomMate.service..")
                .adapter("web", "RoomMate.web")
                .adapter("persistence", "RoomMate.database");


    }

