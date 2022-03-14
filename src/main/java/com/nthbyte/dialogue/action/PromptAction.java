package com.nthbyte.dialogue.action;

import java.util.function.BiConsumer;

public interface PromptAction<T, U> extends BiConsumer<T, U> {}
