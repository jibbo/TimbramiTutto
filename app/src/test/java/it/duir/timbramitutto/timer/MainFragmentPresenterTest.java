package it.duir.timbramitutto.timer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Locale;

import eu.giovannidefrancesco.easysharedprefslib.IStorage;
import it.duir.timbramitutto.model.PunchcardDao;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainFragmentPresenterTest {

  @Mock private IStorage storage;
  @Mock private TimerView view;
  @Mock private PunchcardDao punchcardDao;

  private Locale locale;
  private TimerPresenter presenter;

  @Before
  public void setUp() {
    locale = Locale.getDefault();
    presenter = new TimerFragmentPresenter(view, storage, locale, punchcardDao);
  }

  @Test
  public void testViewResumed() {
    when(storage.get(TimerFragmentPresenter.Companion.getPUNCHCARD_ID(),
                     TimerFragmentPresenter.Companion.getBASE_TIME())).thenReturn(0L);
    presenter.viewResumed();
    verify(storage).get(TimerFragmentPresenter.Companion.getPUNCHCARD_ID(),
                        TimerFragmentPresenter.Companion.getBASE_TIME());
    verify(view, never()).showStartText(anyString());
  }

  @Test
  public void testViewResumedAfterPause() {
    when(storage.get(TimerFragmentPresenter.Companion.getPUNCHCARD_ID(),
                     TimerFragmentPresenter.Companion.getBASE_TIME())).thenReturn(1L);
    presenter.viewPaused();
    presenter.viewResumed();
    verify(storage, times(1)).get(TimerFragmentPresenter.Companion.getPUNCHCARD_ID(),
                                  TimerFragmentPresenter.Companion.getBASE_TIME());
    verify(view).showStartText(anyString());
  }

  @Test
  public void testViewResumedWithTimeStored() {
    final long timeInMillis = Calendar.getInstance(locale).getTimeInMillis();
    when(storage.get(TimerFragmentPresenter.Companion.getPUNCHCARD_ID(),
                     TimerFragmentPresenter.Companion.getBASE_TIME())).thenReturn(timeInMillis);
    presenter.viewResumed();
    verify(storage).get(TimerFragmentPresenter.Companion.getPUNCHCARD_ID(),
                        TimerFragmentPresenter.Companion.getBASE_TIME());
    verify(view).showStartText(anyString());
  }

  @Test
  public void testToggleButtonWhenNoStarted() {
    presenter.toggleTimer();
    verify(view).showStopButton();
    verify(view).showStartText(anyString());
    verify(view).resetEndTime();
    verify(view).resetElapsedTime();
  }

  @Test
  public void testToggleButtonWhenAlreadyStarted() {
    presenter.toggleTimer();
    presenter.toggleTimer();
    verify(view).showPlayButton();
    verify(view).showEndText(anyString());
    verify(view).showElapsedTime(anyString());
  }
}
