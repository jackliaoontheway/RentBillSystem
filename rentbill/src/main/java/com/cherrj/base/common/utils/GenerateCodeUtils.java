package com.cherrj.base.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GenerateCodeUtils {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // ****************************需要修改的部分******************************
    private String srcFolder = System.getProperty("user.dir") + "/src/main/java/";
    private String domainPackageName = "com.cherrj.rentill";
    private String domainName = "Building";
    // *********************************************************************

    public static void main(String[] args) {
        GenerateCodeUtils tool = new GenerateCodeUtils();
        tool.exec();
    }

    protected void exec() {
        if (StringUtils.isEmpty(srcFolder)) {
            logger.error("srcFolder is empty");
            return;
        }
        if (StringUtils.isEmpty(domainPackageName)) {
            logger.error("domainPackageName is empty");
            return;
        }
        if (StringUtils.isEmpty(domainName)) {
            logger.error("domainName is empty");
            return;
        }

        logger.info("Will generate {}'s Repos, Service, ServiceImpl, Controller code.", domainName);
        Map<String, String> codeTypes = new HashMap<>();
        codeTypes.put("", "domain"); //Domain
        codeTypes.put("Repository", "domain");
        codeTypes.put("Service", "service");
        codeTypes.put("ServiceImpl", "service/impl");
        codeTypes.put("Controller", "controller");
        for (String codeType : codeTypes.keySet()) {
            generateCodeByTemplate(domainName, codeType, codeTypes.get(codeType));
        }

    }

    private void generateCodeByTemplate(String domainName, String codeType, String codePath) {

        String folder = srcFolder + domainPackageName.replaceAll("\\.", "/") + "/" + codePath;
        File dir = new File(folder);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = folder + "/" + domainName + codeType + ".java";
        File f = new File(fileName);
        if (f.exists()) {
            logger.info(domainName + "'s base code is exists.");
            return;
        }

        String templateFileContent = null;
        try {
            templateFileContent = getTemplateFileContent(codeType);
            templateFileContent = templateFileContent.replaceAll("\\{domainName\\}", domainName);
            templateFileContent = templateFileContent.replaceAll("\\{domainPackageName\\}", domainPackageName);
            templateFileContent = templateFileContent.replaceAll("\\{lowercaseDomainName\\}", domainName.toLowerCase());
            OutputStream fos = new FileOutputStream(fileName);
            fos.write(templateFileContent.getBytes());
            fos.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private String getTemplateFileContent(String codeType) throws Exception {
        String templateFile = "model." + codeType.toLowerCase() + ".template.txt";

        StringBuilder templateStr = new StringBuilder();
        InputStream fr = new FileInputStream("codetemplate" + templateFile);
        byte[] b = new byte[4096];
        fr.read(b);
        fr.close();
        for (int i = 0; i < b.length && b[i] != 0; i++) {
            templateStr.append((char) b[i]);
        }
        logger.info(templateStr.length() + "");
        String fileContent = templateStr.toString();
        return fileContent;
    }
}
