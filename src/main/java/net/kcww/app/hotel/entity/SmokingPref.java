package net.kcww.app.hotel.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SmokingPref {

  NON_SMOKING("Non-smoking"),
  SMOKING("Smoking");

  private final String label;

}