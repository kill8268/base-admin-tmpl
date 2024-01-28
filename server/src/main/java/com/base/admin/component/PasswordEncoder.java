package com.base.admin.component;

import java.util.Base64;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {
  public String encode(String rawPassword) {
    Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
        .withVersion(Argon2Parameters.ARGON2_VERSION_13)
        .withIterations(10)
        .withMemoryAsKB(65536)
        .withParallelism(1);
    Argon2BytesGenerator generator = new Argon2BytesGenerator();
    generator.init(builder.build());
    byte[] raw = rawPassword.getBytes();
    byte[] hash = new byte[32];
    generator.generateBytes(raw, hash);
    return Base64.getEncoder().encodeToString(hash);
  }

  public boolean matches(String rawPassword, String encodedPassword) {
    return encodedPassword.equals(encode(rawPassword));
  }
}
