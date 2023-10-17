package com.digipay.merchantcredit.service.allocation;

import java.util.List;

import com.digipay.merchantcredit.model.allocation.AllocationJournal;
import com.digipay.merchantcredit.model.allocation.AllocationJournal.Status;

public interface AllocationService {

	AllocationJournal getByEventSettlementTrackingCode(String settlementTrackingCode);

	List<AllocationJournal> getAllByEventTrackingCodes(List<String> trackingCodes);

	List<AllocationJournal> getJournalsByStatuses(String profileId, List<Status> statuses);

	AllocationJournal save(AllocationJournal journal);

	List<AllocationJournal> saveAll(List<AllocationJournal> journal);

}
