package com.example.finalspring.service;

import com.example.finalspring.dto.KatanaDto;

import java.util.List;

public interface KatanaService {
    List<KatanaDto> getAll();
    KatanaDto getById( Long id);
    KatanaDto addKatana(KatanaDto katanaDto);
    KatanaDto updateKatana(Long id, KatanaDto katanaDto);
    boolean deleteKatana(Long id);
}
