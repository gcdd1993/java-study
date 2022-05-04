package io.github.gcdd1993.java.study.thirdpart.samples.poitl;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import org.ddr.poi.html.HtmlRenderPolicy;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:Musk.Chen@fanruan.com">Musk.Chen</a>
 * @since 2022/5/3
 */
public class PoiTlSample {

    public static void main(String[] args) throws IOException {
        HtmlRenderPolicy htmlRenderPolicy = new HtmlRenderPolicy();
        Configure configure = Configure.builder()
                .bind("teachContent", htmlRenderPolicy)
                .bind("plainContent", htmlRenderPolicy)
                .build();
        Map<String, Object> data = new HashMap<>(2);
        data.put("teachContent", FileReader.readFile("/1.html"));
        data.put("plainContent", FileReader.readFile("/2.html"));

        try (InputStream inputStream = PoiTlSample.class.getResourceAsStream("/notes.docx")) {
            XWPFTemplate.compile(inputStream, configure).render(data).writeToFile("target/notes_out.docx");
        }
        // 段落内嵌入html测试
        try (InputStream inputStream = PoiTlSample.class.getResourceAsStream("/poi.docx")) {
            XWPFTemplate.compile(inputStream, configure).render(data).writeToFile("target/poi_out.docx");
        }
    }
}
