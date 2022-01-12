package com.bloom.bloompoi.bak;

import com.bloom.bloompoi.Writer;
import com.bloom.bloompoi.bak.Bloompoi;
import com.bloom.bloompoi.exception.ExcelException;
import com.bloom.bloompoi.model.CardSecret;
import com.bloom.bloompoi.model.User;
import com.bloom.bloompoi.reader.ExcelResult;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

/**
 * Created by mao on 2018/2/21.
 */
public class Examples {

    @Test
    public void testReadList() throws ExcelException {
        List<CardSecret> excelResult = Bloompoi.me().read("/Users/taosy/Documents/卡密列表.xls", CardSecret.class).sheet(0)
            .startRow(1).asList();
        System.out.println(excelResult.size());
    }

    @Test
    public void testReadResult() throws ExcelException {
        ExcelResult<CardSecret> excelResult = Bloompoi.me().read("/Users/taosy/Documents/卡密列表.xls", CardSecret.class)
            .asResult();
        System.out.println(excelResult);
    }

    @Test
    public void testExport() throws ExcelException {
        StopWatch stopWatch = new StopWatch("bloom");
        List<CardSecret> cardSecrets = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            cardSecrets.addAll(this.buildCardSecrets());
        }
        Bloompoi.me().export(Writer.of(cardSecrets)).writeAsFile(new File("/Users/taosy/Documents/卡密列表.xls"));
        System.out.println(stopWatch.getTotalTimeSeconds());
    }

    @Test
    public void testExportBySpEl() throws ExcelException {
        User user = new User();
        user.setName("淘淘");
        user.setList(buildCardSecrets());
        Bloompoi.me().export(Writer.of(user).byTemplate("/Users/taosy/Documents/卡密列表模板.xls"))
            .writeAsFileBySpEl(new File("/Users/taosy/Documents/卡密列表.xls"));
    }

    private List<CardSecret> buildCardSecrets() {
        List<CardSecret> cardSecrets = new ArrayList<>();
        cardSecrets.add(new CardSecret("hua", 1, "vlfdzepjmlz2y43z7er4121212121212121121221", new BigDecimal("20"), 1));
        cardSecrets.add(new CardSecret("hua", 2, "rasefq2rzotsmx526z6g", new BigDecimal("10"), 0));
        cardSecrets.add(new CardSecret("hua", 2, "2ru44qut6neykb2380wt", new BigDecimal("50"), 1));
        cardSecrets.add(new CardSecret("hua", 1, "srcb4c9fdqzuykd6q4zl", new BigDecimal("15"), 0));
        return cardSecrets;
    }
}
