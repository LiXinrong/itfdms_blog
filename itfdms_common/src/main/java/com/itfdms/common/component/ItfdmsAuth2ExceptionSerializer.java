/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.itfdms.common.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.itfdms.common.constant.CommonConstant;
import com.itfdms.common.util.exception.ItfdmsAuth2Exception;
import lombok.SneakyThrows;

/**
 * @author lxr
 * @date 2019/2/1
 * <p>
 * OAuth2 异常格式化
 */
public class ItfdmsAuth2ExceptionSerializer extends StdSerializer<ItfdmsAuth2Exception> {
	public ItfdmsAuth2ExceptionSerializer() {
		super(ItfdmsAuth2Exception.class);
	}

	@Override
	@SneakyThrows
	public void serialize(ItfdmsAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
		gen.writeStartObject();
		gen.writeObjectField("code", CommonConstant.FAIL);
		gen.writeStringField("msg", value.getMessage());
		gen.writeStringField("data", value.getErrorCode());
		gen.writeEndObject();
	}
}
