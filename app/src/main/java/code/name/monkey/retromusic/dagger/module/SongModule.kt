/*
 * Copyright (c) 2019 Hemanth Savarala.
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by
 *  the Free Software Foundation either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package code.name.monkey.retromusic.dagger.module

import code.name.monkey.retromusic.mvp.presenter.SongPresenter
import code.name.monkey.retromusic.mvp.presenter.SongPresenter.SongPresenterImpl
import dagger.Module
import dagger.Provides

/**
 * Created by hemanths on 2019-09-04.
 */
@Module
class SongModule {
    @Provides
    fun providesSongPresenter(presenter: SongPresenterImpl): SongPresenter {
        return presenter
    }
}
