package com.bloom.bloompoi;

import com.bloom.bloompoi.exception.ExcelException;
import com.bloom.bloompoi.model.CardSecret;
import com.bloom.bloompoi.model.User;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Created by mao on 2018/2/21.
 */
public class WriterTest {

    @Test
    public void testExport() throws ExcelException {
        List<CardSecret> cardSecrets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cardSecrets.addAll(this.buildCardSecrets());
        }
        Writer.of(cardSecrets).to(new File("/Users/taosy/Documents/卡密列表.xls"));
    }

    @Test
    public void testExportBySpEl() throws ExcelException {
        User user = new User();
        user.setName("淘淘");
        user.setList(buildCardSecrets());
        Writer.of(user).byTemplate("/Users/taosy/Documents/卡密列表模板.xls").to("/Users/taosy/Documents/卡密列表1.xls");
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
