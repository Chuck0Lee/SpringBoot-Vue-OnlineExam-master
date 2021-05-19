package com.exam.util;

import com.exam.entity.JudgeQuestion;
import com.exam.entity.MultiQuestion;
import com.exam.serviceimpl.JudgeQuestionServiceImpl;
import com.exam.serviceimpl.MultiQuestionServiceImpl;
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
public class ImportJudge_Util {
    @Autowired
    private JudgeQuestionServiceImpl judgeQuestionService;
    /**
     * Excel 解析成数据集合
     *
     * @return
     */
    public static List<JudgeQuestion> excel2Employee2(MultipartFile file) {
        List<JudgeQuestion> list = new ArrayList<>();
        JudgeQuestion judgeQuestion = null;
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
                    judgeQuestion = new JudgeQuestion();
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
                                        judgeQuestion.setSubject(cellValue);
                                        break;
                                    case 1:
                                        judgeQuestion.setQuestion(cellValue);
                                        break;
                                    case 2:
                                        judgeQuestion.setAnswer(cellValue);
                                        break;

                                    case 3:
                                        judgeQuestion.setAnalysis(cellValue);
                                        break;
                                    case 5:
                                        judgeQuestion.setSection(cellValue);
                                        break;

                                }
                                break;
                            // 类型是 Date或者数字 进入此 case 块
                            default: {
                                switch (k) {

                                    case 4:
                                        judgeQuestion.setLevel((int)cell.getNumericCellValue());
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    // 最后将解析后的数据添加到员工集合中
                    list.add(judgeQuestion);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
