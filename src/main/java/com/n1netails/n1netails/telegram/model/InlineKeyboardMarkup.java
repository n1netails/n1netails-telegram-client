package com.n1netails.n1netails.telegram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InlineKeyboardMarkup {
    private List<List<Button>> inline_keyboard;
}
