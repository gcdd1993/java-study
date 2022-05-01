package io.github.gcdd1993.java.study.thirdpart.easypoi;

import cn.afterturn.easypoi.entity.ImageEntity;
import cn.afterturn.easypoi.word.WordExportUtil;
import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @since 2022/4/28
 */
public class EasypoiTest {

    public static void main(String[] args) throws IOException {
        List<Person> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userList.add(new Person("高晨" + i, 10 + i));
        }

        byte[] bytes = EasypoiTest.class.getClassLoader().getResourceAsStream("test.json").readAllBytes();
        Map map = JSON.parseObject(bytes, Map.class);
        List<String> desc1 = (List<String>) map.get("desc1");
        List<Object> desc11 = new ArrayList<>();
        desc1.forEach(r -> {
            if (r.contains("<img")) {
                ImageEntity image = new ImageEntity();
                image.setHeight(200);
                image.setWidth(500);
                image.setUrl(r.replace("<img src='", "").replace("' />", ""));
                image.setType(ImageEntity.URL);
                desc11.add(image);
            } else {
                desc11.add(r);
            }
        });
        map.put("desc1", desc11);
        try {
            XWPFDocument doc = WordExportUtil.exportWord07("C:\\Users\\13983\\OneDrive\\桌面\\报告模板.docx", map);
            String outputFile = "C:\\Users\\13983\\OneDrive\\桌面\\" + System.currentTimeMillis() + ".docx";
            FileOutputStream fos = new FileOutputStream(outputFile);
            doc.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Data
    @AllArgsConstructor
    private static class Person {
        private String name;
        private Integer age;
    }
}
