/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uni.training.camel.camelcafe;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class Customer extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from("timer://myTimer?fixedRate=true&period=60000")
                .process(exchange -> {
                    Order order = new Order(2);
                    order.addItem(DrinkType.ESPRESSO, 2, true);
                    order.addItem(DrinkType.CAPPUCCINO, 4, false);
                    order.addItem(DrinkType.LATTE, 4, false);
                    order.addItem(DrinkType.MOCHA, 2, false);
                    exchange.getIn().setBody(order);
                })
                .to("direct:cafe");

    }


}
