/*
* Copyright 2002 - 2017 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package tests;

import configuration.AppConfig;
import org.exampleProject.qa.common.gui.models.SerpSnippet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import steps.CommonStepDef;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Anton_Shapin on 5/23/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class Simple extends CommonStepDef {

    @Test
    public void first() throws URISyntaxException {
        homePage.open();
        mainSearchFormPage.setSearchRequest("Syma");
        mainSearchFormPage.submit();
        List<SerpSnippet> snippets = serpPage.getSnippets();
        int factSize = serpPage.getSnippets().size();
        assertThat(snippets).as("Check the number of snippets").hasSize(50);
        assertThat(new URI(driver.getCurrentUrl())).hasHost("www.ebay.com")
                .hasNoPort()
                .hasPath("/sch/i.html");
    }
}
