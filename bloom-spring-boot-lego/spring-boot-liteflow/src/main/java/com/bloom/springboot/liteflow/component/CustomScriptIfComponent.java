package com.bloom.springboot.liteflow.component;

import com.yomahub.liteflow.core.NodeIfComponent;
import com.yomahub.liteflow.core.ScriptComponent;
import com.yomahub.liteflow.enums.NodeTypeEnum;
import com.yomahub.liteflow.script.ScriptExecuteWrap;
import com.yomahub.liteflow.script.ScriptExecutorFactory;

import java.util.Map;

/**
 * @author curry
 * Created by on 2023-03-31 11:50 PM
 */
public class CustomScriptIfComponent extends NodeIfComponent implements ScriptComponent {

    @Override
    public boolean processIf() throws Exception {
        ScriptExecuteWrap wrap = new ScriptExecuteWrap();
        wrap.setCurrChainId(this.getCurrChainId());
        wrap.setNodeId(this.getNodeId());
        wrap.setSlotIndex(this.getSlotIndex());
        wrap.setTag(this.getTag());
        wrap.setCmpData(this.getCmpData(Map.class));
        return (boolean) ScriptExecutorFactory.loadInstance()
                .getScriptExecutor(this.getRefNode().getLanguage())
                .execute(wrap);
    }

    @Override
    public void loadScript(String script, String language) {
        ScriptExecutorFactory.loadInstance().getScriptExecutor(language).load(getNodeId(), script);
    }

    public static void main(String[] args) {
        NodeTypeEnum ifScript = NodeTypeEnum.IF_SCRIPT;
        ifScript.setMappingClazz(CustomScriptIfComponent.class);
//        NodeTypeEnum.guessTypeBySuperClazz()
        NodeTypeEnum nodeType = NodeTypeEnum.guessType(CustomScriptIfComponent.class);
        System.out.println(nodeType);
    }
}