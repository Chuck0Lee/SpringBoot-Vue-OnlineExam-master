package com.exam.util;

import com.exam.entity.MultiQuestion;
import com.exam.serviceimpl.MultiQuestionServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class ImportMulti_Util {


    @Autowired
    private MultiQuestionServiceImpl multiQuestionService;
    /**
     * Excel 解析成数据集合
     *
     * @return
     */
    public static List<MultiQuestion> excel2Employee(MultipartFile file) {
        List<MultiQuestion> list = new ArrayList<>();
        MultiQuestion multiQuestion = null;
        try {
            //1. 创建一个 workbook 对象
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            //2. 获取 workbook 中表单的数量
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                //3. 获取表单
                XSSFSheet sheet = workbook.getSheetAt(i);
                //4. 获取表单中的行数
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();
//                System.out.println(physicalNumberOfRows);
//                System.out.println(coloumNum);
                for (int j = 0; j < coloumNum; j++) {
                    //跳过标题行
                    if (j == 0) {
                        continue;//跳过标题行
                    }
                    //6. 获取行
                    XSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//防止数据中间有空行
                    }
                    //7. 获取列数
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    multiQuestion = new MultiQuestion();
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        XSSFCell cell = row.getCell(k);
//                        System.out.println(cell);
//                        System.out.println(cell.getCellType());
                        switch (cell.getCellType()) {
                            // 类型是 String 进入此 case 块
                            case STRING:
                                String cellValue = cell.getStringCellValue();
                                switch (k) {
                                    case 0:
                                        multiQuestion.setSubject(cellValue);
                                        break;
                                    case 1:
                                        multiQuestion.setQuestion(cellValue);
                                        break;
                                    case 2:
                                        multiQuestion.setAnswerA(cellValue);
                                        break;
                                    case 3:
                                        multiQuestion.setAnswerB(cellValue);
                                        break;
                                    case 4:
                                        multiQuestion.setAnswerC(cellValue);
                                        break;
                                    case 5:
                                        multiQuestion.setAnswerD(cellValue);
                                        break;
                                    case 6:
                                        multiQuestion.setRightAnswer(cellValue);
                                        break;
                                    case 7:
                                        multiQuestion.setAnalysis(cellValue);
                                        break;
                                    case 9:
                                        multiQuestion.setSection(cellValue);
                                        break;

                                }
                                break;
                            // 类型是 Date或者数字 进入此 case 块
                            default: {
                                switch (k) {
                                    case 8:
                                        multiQuestion.setScore((int)cell.getNumericCellValue());
                                        break;
                                    case 10:
                                        multiQuestion.setLevel((int)cell.getNumericCellValue());
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    // 最后将解析后的数据添加到题库集合中
                    list.add(multiQuestion);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
