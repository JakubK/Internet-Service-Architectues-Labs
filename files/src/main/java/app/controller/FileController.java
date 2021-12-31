package app.controller;

import app.model.File;
import app.repository.FileRepository;
import org.hibernate.annotations.NotFound;
import org.hibernate.id.GUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("api/files")
public class FileController {

  private final Path filesLocation;

  private final FileRepository fileRepository;

  @Autowired
  public FileController(FileRepository repo) throws Exception {
    this.fileRepository = repo;
    this.filesLocation = Paths.get("/files")
            .toAbsolutePath()
            .normalize();
    try
    {
      Files.createDirectories(this.filesLocation);
    }
    catch (Exception ex) {
      throw new Exception(ex.getMessage(), ex);
    }
  }

  @PostMapping
  public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") int id) throws IOException {
    String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
    String fileName = id + fileExtension;
    Path targetLocation = this.filesLocation.resolve(fileName);
    Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
    fileRepository.save(File.builder()
            .id(id)
            .filePath(targetLocation.toString())
            .build());
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity deleteFile(@PathVariable("id") int id)
  {
    if(fileRepository.existsById(id)) {
      var f = fileRepository.findById(id).get();
      java.io.File fi = new java.io.File(f.getFilePath());
      fi.delete();
      fileRepository.delete(f);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }
}
