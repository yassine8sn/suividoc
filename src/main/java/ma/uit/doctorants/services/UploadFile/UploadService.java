package ma.uit.doctorants.services.UploadFile;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.transaction.Transactional;

@Service
public class UploadService {
	@Transactional
	public String UploadFile(MultipartFile file){


		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				System.out.println(file.getOriginalFilename());
				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				System.out.println(System.getProperty("user.dir"));
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + file.getOriginalFilename());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				

				return serverFile.getAbsolutePath();
			} catch (Exception e) {
				return "faild";
			}
		} else {
			return "empty";
		}
	}
}
