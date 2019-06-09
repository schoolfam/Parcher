package com.schoolfam.parcher.Fragments.TeacherFragments


import android.os.Bundle
import android.transition.Visibility
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.schoolfam.parcher.R
import com.schoolfam.parcher.data.assessmentType.AssessmentType
import com.schoolfam.parcher.data.subject.Subject
import com.schoolfam.parcher.data.user.User
import com.schoolfam.parcher.viewModel.*
import kotlinx.android.synthetic.main.fragment_teacher_view_assessment.*

class TeacherViewAssessmentFragment : Fragment() {


    private lateinit var studentViewModel: StudentViewModel
    private lateinit var parntViewModel: ParentViewModel
    private lateinit var teacherViewModel: TeacherViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var sectionViewModel: SectionViewModel
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var assessmentViewModel: AssessmentViewModel
    private lateinit var assessmentTypeViewModel: AssessmentTypeViewModel
    private lateinit var sectionSubjectViewModel: SectionSubjectViewModel

    var student: User? = null
    var sectionId:Long? = null


    private lateinit var studentNameTextView: TextView
    private lateinit var subjectSpinner: Spinner
    private lateinit var assessmentTypeSpinner: Spinner
    private lateinit var editAssessmentButton: Button
    private lateinit var deleteAssessmentButton: Button
    private lateinit var assessmentTypeTextView: TextView
    private lateinit var assessmentLayout: LinearLayout
    private lateinit var scoreEditText: EditText
    private lateinit var viewAssessmentButton: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teacher_view_assessment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val subjects:MutableList<Subject> = mutableListOf()
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        parntViewModel = ViewModelProviders.of(this).get(ParentViewModel::class.java)
        teacherViewModel = ViewModelProviders.of(this).get(TeacherViewModel::class.java)
        sectionViewModel = ViewModelProviders.of(this).get(SectionViewModel::class.java)
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel::class.java)
        assessmentViewModel = ViewModelProviders.of(this).get(AssessmentViewModel::class.java)
        assessmentTypeViewModel = ViewModelProviders.of(this).get(AssessmentTypeViewModel::class.java)
        sectionSubjectViewModel = ViewModelProviders.of(this).get(SectionSubjectViewModel::class.java)

        student = arguments?.getSerializable("current_student") as User?
        sectionId = arguments?.getLong("section_id")

        studentNameTextView = assessment_view_student_name
        subjectSpinner = view_subject_spinner
        assessmentTypeSpinner = view_assessment_type_spinner

        editAssessmentButton = edit_assessment_button
        deleteAssessmentButton = delete_assessment_button
        assessmentLayout = assessment_layout
        assessmentTypeTextView = assessment_type_text_view
        scoreEditText = score_edit_text
        viewAssessmentButton = view_assessment_button

        studentNameTextView.text = student!!.fname+" "+ student!!.lname


        assessmentTypeViewModel.allAssessmentTypes.observe(this, Observer { assessmentTypes -> assessmentTypes?.let {

            val adapter: ArrayAdapter<AssessmentType> = ArrayAdapter(activity,android.R.layout.simple_spinner_item,it)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            assessmentTypeSpinner.adapter = adapter
            }
        })

        sectionSubjectViewModel.findSectionSubjectBySectionId(sectionId!!).observe(this, Observer {
                sectionSubject -> sectionSubject?.let {


            sectionSubject.forEach {
                    sectionSubject ->sectionSubject?.let {
                subjectViewModel.findSubjectById(sectionSubject.subjectId).observe(this, Observer {
                        subject-> subjects.add(subject)
                    val adapter: ArrayAdapter<Subject> = ArrayAdapter(activity,android.R.layout.simple_spinner_item,subjects)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    subjectSpinner.adapter = adapter
                })
            }

            }
        }
        })

        viewAssessmentButton.setOnClickListener {

            assessmentViewModel.findAssessmentByStudentIdAndSubjectId((subjectSpinner.selectedItem as Subject).id,student!!.id!!).observe(this,
                Observer { assessments->
                   val mAssessments = assessments.find {assessment -> assessment.assessmentTypeId == (assessmentTypeSpinner.selectedItem as AssessmentType).id }
                    assessmentTypeTextView.text = (assessmentTypeSpinner.selectedItem as AssessmentType).assessment_name
                    if(mAssessments!=null){
                        scoreEditText.setText(mAssessments!!.score.toString())
                    }
                    else{
                        scoreEditText.setText("")
                    }

                    assessmentLayout.visibility = View.VISIBLE



                })
        }





    }
}
